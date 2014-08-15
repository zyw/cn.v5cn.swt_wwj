package cn.v5cn.swt_wwj.models;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class LayerCheckBox {
	private Button checkBox;
	
	public LayerCheckBox(Composite parent,String label,boolean isSelected){
		checkBox = new Button(parent, SWT.CHECK);
		checkBox.setText(label);
		checkBox.setSelection(isSelected);
	}
	
	public void setSelection(boolean isSelected){
		this.checkBox.setSelection(isSelected);
	}
	
	public boolean getSelection(){
		return this.checkBox.getSelection();
	}
	
	public void setText(String label){
		this.checkBox.setText(label);
	}
	
	public String getText(){
		
		return this.checkBox.getText();
	}
	
	public void addSelectionListener(SelectionAdapter listener){
		checkBox.addSelectionListener(listener);
	}
	
}
