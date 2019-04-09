package com.engine.handle;

import java.util.HashMap;
import java.util.Map;

import com.experian.stratman.datasources.runtime.IData;

public class EngineData implements IData {

	private String layoutName = null;
	private Map<String, Object> contents = null;
	
	
	public EngineData(String layoutName){
		this.layoutName = layoutName;
		contents = new HashMap<String, Object>();
	}
	
	@Override
	public String getLayout() {
		return layoutName;
	}

	@Override
	public Object getValue(String name) {
		return contents.get(name);
	}

	@Override
	public void setValue(String name, Object value) {
		contents.put(name, value);
	}
	
	/**
	 * 批量赋值
	 * @param map
	 */
	public void setContents(Map<String, Object> map){
		contents.clear();
		contents = map;
	}
	
	/**
	 * 批量取值
	 * @return
	 */
	public Map<String, Object> getContents(){
		return contents;
	}
	
	@Override
	public String toString(){
		StringBuilder sb = new StringBuilder(layoutName);
		sb.append("--->");
		if(contents != null)
			sb.append(contents.toString());
		else
			sb.append("null");
		return sb.toString();
	}

}
