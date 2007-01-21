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
package org.seasar.gusuku.servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.gusuku.GusukuConstant;
import org.seasar.gusuku.util.PropertyUtil;

public class DownLoadServlet extends HttpServlet {

	private static final long serialVersionUID = 4486540540643907440L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("**DownLoadServlet**RequestURI:"+request.getRequestURI());
		String[] pathInfo = getPathInfo(request.getRequestURI());
		System.out.println("ID=" + pathInfo[0]);
		System.out.println("Filename=" + pathInfo[1]);
		
		try{
			BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			//response.setHeader("Content-disposition", "attachment; filename=\""+URLEncoder.encode(pathInfo[1],"UTF-8")+"\"");
			String path= PropertyUtil.getProperty(GusukuConstant.UPLOAD_DIR_KEY);
			if(!path.endsWith(File.separator)){
				path = path + File.separator;
			}
			BufferedInputStream in = new BufferedInputStream(new FileInputStream(path + pathInfo[0]));
			int data = 0;
			while((data=in.read()) != -1){
				out.write(data);
			}
			out.close();
			in.close();
		}catch(Exception e){
			
		}
	}
	
	private String[] getPathInfo(String path){
		String info = path.substring(path.indexOf("download/") + "download/".length());
		String[] result = new String[2];
		StringTokenizer st = new StringTokenizer(info,"/");
		result[0] = st.nextToken();
		result[1] = st.nextToken();
		return result;
	}
	
	

}
