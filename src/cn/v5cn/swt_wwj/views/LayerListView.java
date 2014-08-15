package cn.v5cn.swt_wwj.views;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.ViewPart;

import cn.v5cn.swt_wwj.models.LayerCheckBoxModel;
import cn.v5cn.swt_wwj.wwj.SingleWorldWindow;

public class LayerListView extends ViewPart {
	
	public static final String ID = "cn.v5cn.swt_wwj.views.layerlistview";
	
	@Override
	public void createPartControl(Composite parent) {
		Composite checkBoxList = new Composite(parent, SWT.NONE);
		checkBoxList.setLayout(new RowLayout(1));
		LayerCheckBoxModel lcbm = new LayerCheckBoxModel(checkBoxList, SingleWorldWindow.getInstance(true)); 
	}

	@Override
	public void setFocus() {

	}

}
