package cn.v5cn.swt_wwj.actions;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.event.SelectEvent;
import gov.nasa.worldwind.event.SelectListener;
import gov.nasa.worldwind.geom.Sector;
import gov.nasa.worldwind.layers.RenderableLayer;
import gov.nasa.worldwind.render.SurfaceImage;
import gov.nasa.worldwind.util.BasicDragger;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.IWorkbenchWindowActionDelegate;

import cn.v5cn.swt_wwj.wwj.SingleWorldWindow;

/**
 * Our sample action implements workbench action delegate.
 * The action proxy will be created by the workbench and
 * shown in the UI. When the user tries to use the action,
 * this delegate will be created and execution will be 
 * delegated to it.
 * @see IWorkbenchWindowActionDelegate
 */
public class SurfaceImageAction implements IWorkbenchWindowActionDelegate {
	
	public static final String ID = "cn.v5cn.swt_wwj.actions.surfaceImageAction";
	
	private IWorkbenchWindow window;
	/**
	 * The constructor.
	 */
	public SurfaceImageAction() {
	}
//弹出框
//	MessageDialog.openInformation(
//	window.getShell(),
//	"%Bundle-Name",
//	"Hello, Eclipse world");
	
	/**
	 * The action has been activated. The argument of the
	 * method represents the 'real' action sitting
	 * in the workbench UI.
	 * @see IWorkbenchWindowActionDelegate#run
	 */
	public void run(IAction action) {
		FileDialog fileDialog = new FileDialog(window.getShell(), SWT.OPEN);
		fileDialog.setFilterExtensions(new String[]{"*.jpg","*.png"});
		String imagePath = fileDialog.open();
		if(null != imagePath){
			final WorldWindow ww = SingleWorldWindow.getInstance(true);
			SurfaceImage si = new SurfaceImage(imagePath,Sector.fromDegrees(35, 45, -115, -95));
			RenderableLayer layer = new RenderableLayer();
			layer.addRenderable(si);
			ww.getModel().getLayers().add(layer);
			
			ww.addSelectListener(new SelectListener() {
				private BasicDragger dragger = new BasicDragger(ww);
				@Override
				public void selected(SelectEvent e) {
					this.dragger.selected(e);
				}
			});
		}
	}

	/**
	 * Selection in the workbench has been changed. We 
	 * can change the state of the 'real' action here
	 * if we want, but this can only happen after 
	 * the delegate has been created.
	 * @see IWorkbenchWindowActionDelegate#selectionChanged
	 */
	public void selectionChanged(IAction action, ISelection selection) {
	}

	/**
	 * We can use this method to dispose of any system
	 * resources we previously allocated.
	 * @see IWorkbenchWindowActionDelegate#dispose
	 */
	public void dispose() {
	}

	/**
	 * We will cache window object in order to
	 * be able to provide parent shell for the message dialog.
	 * @see IWorkbenchWindowActionDelegate#init
	 */
	public void init(IWorkbenchWindow window) {
		this.window = window;
	}
}