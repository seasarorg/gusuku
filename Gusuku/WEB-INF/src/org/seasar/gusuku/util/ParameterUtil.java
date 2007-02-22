/*
 * Copyright 2004-2007 the Seasar Foundation and the Others.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied. See the License for the specific language
 * governing permissions and limitations under the License.
 */
package org.seasar.gusuku.util;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.seasar.framework.util.StringUtil;

public class ParameterUtil {

	/**
	 * Mapから値(String)を取得する ParameterAware用
	 * @param parameters Map
	 * @param parameter　Parameter
	 * @return String
	 */
	public static String getParameterValue(Map parameters,String parameter) {
		String[] value = (String[]) parameters.get(parameter);
		if(value == null){
			return null;
		}
		return (String)value[0];
	}
	public static String getParameterCsvValue(Map parameters,String parameter) {
		String[] value = (String[]) parameters.get(parameter);
		if(value == null){
			return null;
		}
		StringBuffer sb = new StringBuffer();
		for(int i=0;i<value.length;i++){
			if(i > 0){
				sb.append(",");
			}
			sb.append(value[i]);
		}
		return sb.toString();
	}
	
	/**
	 * Mapから値(String[])を取得する ParameterAware用
	 * @param parameters Map
	 * @param parameter　Parameter
	 * @return String[]
	 */
	public static String[] getParameterStringArrayValue(Map parameters,String parameter){
		return (String[]) parameters.get(parameter);
	}
	/**
	 * Mapから値(Long[])を取得する ParameterAware用
	 * @param parameters Map
	 * @param parameter　Parameter
	 * @return Long[]
	 */
	public static Long[] getParameterLongArrayValue(Map parameters,String parameter){
		//TODO 型変換
		String[] projectids = (String[]) parameters.get(parameter);
		if(projectids == null){
			return null;
		}
		Long[] ids = new Long[projectids.length];
		for(int i = 0 ;i<projectids.length ; i++){
			ids[i] = new Long(projectids[i]);
		}
		return ids;
	}
	
	/**
	 * Mapから値(Date)を取得する ParameterAware用
	 * @param parameters Map
	 * @param parameter　Parameter
	 * @return Date
	 */
	public static Date getParameterDateValue(Map parameters,String parameter) {
		Date result = null;
		String value = getParameterValue(parameters,parameter);
		if(StringUtil.isEmpty(value)){
			return result;
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		try{
			result = format.parse(value);
		}catch(ParseException e){
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Mapから値(File)を取得する ParameterAware用
	 * @param parameters Map
	 * @param parameter　Parameter
	 * @return File
	 */
	public static File getParameterFileValue(Map parameters,String parameter) {
		File[] value = (File[]) parameters.get(parameter);
		if(value == null){
			return null;
		}
		return value[0];
	}
	
	public static void putParameterValue(Map<String,String[]> parameters,String key,String value){
		parameters.put(key,new String[]{value});
	}
	public static void putParameterCsvValue(Map<String,String[]> parameters,String key,String value){
		parameters.put(key,splitValue(value));
	}
	public static void putParameterValue(Map<String,String[]> parameters,String key,String[] value){
		parameters.put(key,value);
	}
	public static void putParameterValue(Map<String,String[]> parameters,String key,Long[] value){
		//TODO
		String[] s = new String[value.length];
		for(int i=0;i<value.length;i++){
			s[i] = Long.toString(value[i]);
		}
		parameters.put(key,s);
	}
	public static void putParameterValue(Map<String,String[]> parameters,String key,Date value){
		if(value != null){
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			parameters.put(key,new String[]{format.format(value)});
		}
	}
	
	public static void putParameterValue(Map<String,String[]> parameters,String key,Long value){
		if(value != null){
			parameters.put(key,new String[]{value.toString()});
		}
	}
	
	public static String[] splitValue(String value){
		if(value == null){
			return null;
		}
		List<String> result = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(value,",");
		while(st.hasMoreTokens()){
			result.add(st.nextToken().trim());
		}
		
		return (String[])result.toArray(new String[0]);
		
	}

	public static Long getParameterLongValue(Map parameters, String parameter) {
		String[] value = (String[]) parameters.get(parameter);
		if(value == null || StringUtil.isEmpty(value[0])){
			return null;
		}
		return new Long(value[0]);
	}
}
