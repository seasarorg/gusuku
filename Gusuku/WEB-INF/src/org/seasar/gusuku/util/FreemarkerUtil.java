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
