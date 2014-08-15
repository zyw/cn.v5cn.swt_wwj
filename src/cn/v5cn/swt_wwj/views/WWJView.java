package cn.v5cn.swt_wwj.views;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Panel;

import org.eclipse.swt.SWT;
import org.eclipse.swt.awt.SWT_AWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import cn.v5cn.swt_wwj.status.StatusBarData;
import cn.v5cn.swt_wwj.wwj.SingleWorldWindow;

public class WWJView extends ViewPart {
	public WWJView() {
	}

	public static final String ID = "cn.v5cn.swt_wwj.views.wwjview";
	private static WorldWindow wwd = null;
	
	static{
		initWorldWindLayerModel();
	}
	
	@Override
	public void createPartControl(Composite parent) {
		Composite top = new Composite(parent, SWT.EMBEDDED);
		top.setLayoutData(new GridData(GridData.FILL_BOTH));
		Frame worldFrame = SWT_AWT.new_Frame(top);
		Panel panel = new Panel(new BorderLayout());
		worldFrame.add(panel);
		panel.add((Component) wwd,BorderLayout.CENTER);
		parent.setLayoutData(new GridData(GridData.FILL_BOTH));
		//添加状态栏数据绑定
		new StatusBarData().setDataSource(wwd);
				
		//测试状态栏
//		IStatusLineManager status = getViewSite().getActionBars().getStatusLineManager();
//		StatusLineContributionItem t  = new StatusLineContributionItem("ddd3",-1);
//		t.setVisible(true);
//		t.setText("测试ret");
//		status.add(t);
	}
	
	static void initWorldWindLayerModel(){
		//Model model = (Model)WorldWind.createConfigurationComponent(AVKey.MODEL_CLASS_NAME);
		//wwd = SingleWorldWindow.getInstance(model);
		
		wwd = SingleWorldWindow.getInstance(true);
	}
	
	public static void repaint(){
		((WorldWindowGLCanvas)wwd).repaint();
	}
	@Override
	public void setFocus() {
		
	}

}
