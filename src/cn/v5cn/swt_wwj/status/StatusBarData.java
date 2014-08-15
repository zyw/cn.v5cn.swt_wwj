package cn.v5cn.swt_wwj.status;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.PositionEvent;
import gov.nasa.worldwind.event.PositionListener;
import gov.nasa.worldwind.event.RenderingEvent;
import gov.nasa.worldwind.event.RenderingListener;
import gov.nasa.worldwind.geom.Angle;
import gov.nasa.worldwind.geom.Position;
import gov.nasa.worldwind.util.WWMath;

import java.awt.EventQueue;

import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.swt.widgets.Display;

import cn.v5cn.swt_wwj.util.ContributionPool;

public class StatusBarData implements PositionListener,RenderingListener {
	
	private WorldWindow dataSource;
	
	public final static String UNIT_METRIC = "gov.nasa.worldwind.StatusBar.Metric";
	public final static String UNIT_IMPERIAL = "gov.nasa.worldwind.StatusBar.Imperial";
	
	private String elevationUnit = UNIT_METRIC;
	private String angleFormat = Angle.ANGLE_FORMAT_DD;
	
	private ContributionPool cpool = ContributionPool.getInstance();
	private StatusLineContributionItem  longitude = null;		//经度
	private StatusLineContributionItem  latitude = null;		//纬度
	private StatusLineContributionItem  altitude = null;		//高度
	private StatusLineContributionItem  elevation = null;		//海拔
	
	public StatusBarData(){
		longitude = cpool.getContrbution("longitude");		//经度
		latitude = cpool.getContrbution("latitude");		//纬度
		altitude = cpool.getContrbution("altitude");		//高度
		elevation = cpool.getContrbution("elevation");
	}
	
	@Override
	public void stageChanged(RenderingEvent event) {
		if (!event.getStage().equals(RenderingEvent.BEFORE_BUFFER_SWAP))
			return;

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				if (dataSource.getView() != null && dataSource.getView().getEyePosition() != null){
					Display.getDefault().asyncExec(new Runnable() {
						@Override
						public void run() {
							altitude.setText(makeEyeAltitudeDescription(dataSource.getView().getEyePosition().getElevation()));
						}
					});
				}else
					altitude.setText("");
			}
		});
	}

	@Override
	public void moved(PositionEvent event) {

		handleCursorPositionChange(event);
		
	}
	
	public void setDataSource(WorldWindow dataSource){
		if(dataSource != null){
			dataSource.removePositionListener(this);
			dataSource.removeRenderingListener(this);
		}
		dataSource.addPositionListener(this);
		dataSource.addRenderingListener(this);
		
		this.dataSource = dataSource;
	}
	
	protected String makeCursorElevationDescription(double metersElevation) {
		String s;
		String elev = "海拔:";
		if (UNIT_IMPERIAL.equals(elevationUnit))
			s = String.format(elev + " %,7d 英尺",(int) (WWMath.convertMetersToFeet(metersElevation)));
		else
			// Default to metric units.
			s = String.format(elev + " %,7d 米", (int) metersElevation);
		return s;
	}
	
	protected String makeEyeAltitudeDescription(double metersAltitude) {
		String s;
		String altitude = "高度:";
		if (UNIT_IMPERIAL.equals(elevationUnit))
			s = String.format(altitude + " %,7d mi", (int) Math.round(WWMath.convertMetersToMiles(metersAltitude)));
		else
			// Default to metric units.
			s = String.format(altitude + " %,7d 千米",(int) Math.round(metersAltitude / 1e3));
		return s;
	}
	
	protected String makeAngleDescription(String label, Angle angle) {
		String s;
		if (Angle.ANGLE_FORMAT_DMS.equals(angleFormat))
			s = String.format("%s %s", label, angle.toDMSString());
		else
			s = String.format("%s %7.4f\u00B0", label, angle.degrees);
		return s;
	}
	
	protected void handleCursorPositionChange(final PositionEvent event){
		Display.getDefault().asyncExec(new Runnable() {
			@Override
			public void run() {
				Position newPos = event.getPosition();
				if(newPos != null){
					String las = makeAngleDescription("纬度", newPos.getLatitude());
					String los = makeAngleDescription("经度", newPos.getLongitude());
					String els = makeCursorElevationDescription(dataSource.getModel()
							.getGlobe()
							.getElevation(newPos.getLatitude(), newPos.getLongitude()));
					latitude.setText(las);
					longitude.setText(los);
					elevation.setText(els);
				}else{
					latitude.setText("");
					longitude.setText("");
					elevation.setText("");
				}
			}
		});
	}

}
