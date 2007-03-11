package org.seasar.gusuku.util;

import java.util.ArrayList;
import java.util.List;

import org.seasar.gusuku.dto.SelectValue;


public class SelectValueUtil{
	
	public static List<SelectValue> getSortKeyList(){
		List<SelectValue> list = new ArrayList<SelectValue>();
		list.add(new SelectValue("TYPE","タイプ"));
		list.add(new SelectValue("KEY","KEY"));
		list.add(new SelectValue("TITLE","件名"));
		list.add(new SelectValue("PRIORITY","優先度"));
		list.add(new SelectValue("STATUS","ステータス"));
		list.add(new SelectValue("ASSIGNEE","アサイン先"));
		list.add(new SelectValue("REPORTER","報告者"));
		list.add(new SelectValue("RDATE","報告日"));
		return list;
	}
	
	public static List<SelectValue> getOrderList(){
		List<SelectValue> list = new ArrayList<SelectValue>();
		list.add(new SelectValue("ASC","昇順"));
		list.add(new SelectValue("DESC","降順"));
		return list;
	}

}
