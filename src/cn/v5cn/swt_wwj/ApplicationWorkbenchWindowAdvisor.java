package cn.v5cn.swt_wwj;

import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

public class ApplicationWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

    public ApplicationWorkbenchWindowAdvisor(IWorkbenchWindowConfigurer configurer) {
        super(configurer);
    }

    public ActionBarAdvisor createActionBarAdvisor(IActionBarConfigurer configurer) {
        return new ApplicationActionBarAdvisor(configurer);
    }
    
    public void preWindowOpen() {
        IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
        configurer.setInitialSize(new Point(1200, 800));
        configurer.setShowCoolBar(true);
        //默认是true，所以这里就不用设置了
        //configurer.setShowMenuBar(true);
        configurer.setShowProgressIndicator(true);
        configurer.setShowStatusLine(true);
    }
    @Override
    public void postWindowOpen() {
    	super.postWindowOpen();
//    	IStatusLineManager statusLine = getWindowConfigurer().getActionBarConfigurer().getStatusLineManager();
//    	statusLine.setMessage("ddddddddd");
    }
    @Override
    public void dispose() {
    	super.dispose();
    }
}
