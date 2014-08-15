package cn.v5cn.swt_wwj.models;

import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

public class LayerCheckBoxModel {
	private List<LayerCheckBox> lcbs = new ArrayList<LayerCheckBox>();
	
	public LayerCheckBoxModel(Composite parent,WorldWindow ww){
		LayerList layers = ww.getModel().getLayers();
		LayerCheckBox lcb = null;
		for(Layer layer : layers){
			System.out.println(layer.getName());
			lcb = new LayerCheckBox(parent, layer.getName(), layer.isEnabled());
			LayerCheckBoxSelection selection = new LayerCheckBoxSelection(ww, layer, layer.isEnabled());
			lcb.addSelectionListener(selection);
			lcbs.add(lcb);
		}
	}
	
	public List<LayerCheckBox> elements(){
		return lcbs;
	}
	
	public static class LayerCheckBoxSelection extends SelectionAdapter{
		
		public WorldWindow ww;
		
		public Layer layer;
		
		public boolean selected;
		
		public LayerCheckBoxSelection(WorldWindow ww,Layer layer,boolean selected){
			super();
			this.ww = ww;
			this.layer = layer;
			this.selected = selected;
			this.layer.setEnabled(selected);
		}
		
		public void widgetSelected(SelectionEvent e) {
			Button cb = (Button)e.widget;
			this.layer.setEnabled(cb.getSelection());
			this.ww.redraw();
		}
		
	}
	
}

