package cn.v5cn.swt_wwj.wwj;

import gov.nasa.worldwind.BasicModel;
import gov.nasa.worldwind.Model;
import gov.nasa.worldwind.WorldWindow;
import gov.nasa.worldwind.awt.WorldWindowGLCanvas;
import gov.nasa.worldwind.globes.Globe;
import gov.nasa.worldwind.layers.CompassLayer;
import gov.nasa.worldwind.layers.Layer;
import gov.nasa.worldwind.layers.LayerList;
import gov.nasa.worldwind.layers.ViewControlsLayer;
import gov.nasa.worldwind.layers.ViewControlsSelectListener;

public class SingleWorldWindow {
	private static WorldWindow ww;
	private static Model model;
	private static boolean includeVCLayer = false;
	private static ViewControlsLayer vclayer = new ViewControlsLayer();
	
	private SingleWorldWindow(){
	}
	
	public static WorldWindow getInstance(){
		if(model == null)
			return SingleWorldWindow.getInstance(new BasicModel(),false);
		else
			return SingleWorldWindow.getInstance(model,false);
	}
	
	public static WorldWindow getInstance(boolean includeVCLayer){
		if(model == null)
			return SingleWorldWindow.getInstance(new BasicModel(),includeVCLayer);
		else
			return SingleWorldWindow.getInstance(model,includeVCLayer);
	}
	
	public static WorldWindow getInstance(Model model,boolean includeVCLayer){
		SingleWorldWindow.includeVCLayer = includeVCLayer;
		if(ww == null){
			ww = new WorldWindowGLCanvas();
			ww.setModel(model);
			if(includeVCLayer){
				insertBeforeCompass(ww,vclayer);
				ww.addSelectListener(new ViewControlsSelectListener(ww, vclayer));
			}
			SingleWorldWindow.model = model;
		}
		return ww;
	}
	
	public static WorldWindow getInstance(Globe globe,LayerList lls){
		if(model == null){
			model = new BasicModel(globe, lls);
			return SingleWorldWindow.getInstance(model,false);
		}else{
			return SingleWorldWindow.getInstance(model,false);
		}
	}
	
	public static Model getModel(){
		return model;
	}
	public static void setModel(Model model){
		SingleWorldWindow.model = model;
	}
	
	public static void insertBeforeCompass(WorldWindow ww,Layer layer){
		int compassPosition = 0;
		LayerList layerList = ww.getModel().getLayers();
		for(Layer l : layerList){
			if(l instanceof CompassLayer)
				compassPosition = layerList.indexOf(l);
		}
		layerList.add(compassPosition, layer);
	}
	
	public static boolean getIncludeVCLayer(){
		return SingleWorldWindow.includeVCLayer;
	}
}
