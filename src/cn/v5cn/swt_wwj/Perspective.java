package cn.v5cn.swt_wwj;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import cn.v5cn.swt_wwj.views.LayerListView;
import cn.v5cn.swt_wwj.views.WWJView;

public class Perspective implements IPerspectiveFactory {
	
	public static final String ID = "cn.v5cn.swt_wwj.perspective";
	
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		//隐藏默认的视图
		layout.setEditorAreaVisible(false);
		//为列表视图创建视图组，实际上IFolderLayout就相当与一个视图组
		IFolderLayout folderLeft = layout.createFolder("left", IPageLayout.LEFT, 0.16f, editorArea);
		folderLeft.addView(LayerListView.ID);
		
		//为地球创建一个视图组
		IFolderLayout folderMain = layout.createFolder("main", IPageLayout.RIGHT, 0.8f, editorArea);
		folderMain.addPlaceholder(WWJView.ID + ":*");
		folderMain.addView(WWJView.ID);
		//让地图视图不能关闭
		layout.getViewLayout(WWJView.ID).setCloseable(false);
	}
}
