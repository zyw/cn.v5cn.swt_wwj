package cn.v5cn.swt_wwj;

import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.StatusLineContributionItem;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;

import cn.v5cn.swt_wwj.util.ApplicationConstants;
import cn.v5cn.swt_wwj.util.ContributionPool;

public class ApplicationActionBarAdvisor extends ActionBarAdvisor {

	IWorkbenchAction exitAction;
	
	StatusLineContributionItem  longitude;		//经度
	StatusLineContributionItem  latitude;		//纬度
	StatusLineContributionItem  altitude;		//高度
	StatusLineContributionItem  elevation;		//海拔
	
    public ApplicationActionBarAdvisor(IActionBarConfigurer configurer) {
        super(configurer);
    }

    protected void makeActions(IWorkbenchWindow window) {
    	//创建退出菜单
    	exitAction = ActionFactory.QUIT.create(window);
    	register(exitAction);
    	
    	longitude = new StatusLineContributionItem("longitude", 20);
    	longitude.setText("经度:0");
    	longitude.setVisible(true);
    	ContributionPool.getInstance().setContrbution("longitude", longitude);
    	
    	latitude = new StatusLineContributionItem("latitude", 20);
    	latitude.setText("纬度:0");
    	latitude.setVisible(true);
    	ContributionPool.getInstance().setContrbution("latitude", latitude);
    	
    	altitude = new StatusLineContributionItem("altitude", 20);
    	altitude.setText("高度:0千米");
    	altitude.setVisible(true);
    	ContributionPool.getInstance().setContrbution("altitude", altitude);
    	
    	elevation = new StatusLineContributionItem("elevation", 20);
    	elevation.setText("海拔:0米");
    	elevation.setVisible(true);
    	ContributionPool.getInstance().setContrbution("elevation", elevation);
    	
    }

    protected void fillMenuBar(IMenuManager menuBar) {
    	MenuManager fileMenu = new MenuManager("文件",ApplicationConstants.FILE_MENU);
    	menuBar.add(fileMenu);
    	menuBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
    	fileMenu.add(exitAction);
    }
    
    @Override
    protected void fillStatusLine(IStatusLineManager statusLine) {
    	//在状态栏创建之前这个方法就被调用了，所以不能在这个里面设置参数
//    	contribution.setVisible(true);
    	statusLine.add(longitude);
    	statusLine.add(latitude);
    	statusLine.add(altitude);
    	statusLine.add(elevation);
    }
}
