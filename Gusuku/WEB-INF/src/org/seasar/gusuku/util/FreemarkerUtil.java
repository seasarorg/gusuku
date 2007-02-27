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
import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import com.opensymphony.webwork.ServletActionContext;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class FreemarkerUtil {
	
	public static final String TEMPLATE_REPORT = "report.template";
	public static final String TEMPLATE_COMMENT = "comment.template";
	
	private static Configuration configuration = new Configuration();
	private static boolean initialized = false;
	
	private static Configuration getDefaultConfiguration(){
		if(!initialized){
			try{
				String path = ServletActionContext.getServletContext().getRealPath("WEB-INF"+File.separator+"template");
				configuration.setDirectoryForTemplateLoading(new File(path));
			}catch(IOException e){
				System.out.println(e);
			}
		}
		return configuration;
	}
	
	public static String merge(Map params,String templateName){
		StringWriter out = null;
		try{
			out = new StringWriter();
			Configuration cfg = getDefaultConfiguration();
			Template template = cfg.getTemplate(templateName);
			template.process(params,out);
			out.flush();
			return out.toString();
		}catch(TemplateException e){
			System.out.println(e);
		}catch(IOException e){
			System.out.println(e);
		}finally{
			if(out != null){
				try{
					out.close();	
				}catch(IOException ie){
					System.out.println(ie);
				}
			}
			
		}
		return "";
	}

}
