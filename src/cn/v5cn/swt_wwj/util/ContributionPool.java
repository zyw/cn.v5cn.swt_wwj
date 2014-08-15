package cn.v5cn.swt_wwj.util;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.action.StatusLineContributionItem;

public class ContributionPool {
	private final Map<String,StatusLineContributionItem> cmap = new HashMap<String,StatusLineContributionItem>();
	
	private static final ContributionPool cpool = new ContributionPool();
	
	private ContributionPool(){}
	
	public static ContributionPool getInstance(){
		return cpool;
	}
	
	public void setContrbution(String key,StatusLineContributionItem item){
		if(!cmap.containsKey(key)){
			cmap.put(key, item);
		}
	}
	
	public StatusLineContributionItem getContrbution(String key){
		if(cmap.containsKey(key)){
			return cmap.get(key);
		}
		return null;
	}
	
}
