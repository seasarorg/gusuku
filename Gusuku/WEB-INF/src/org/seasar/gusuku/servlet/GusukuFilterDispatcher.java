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

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.regex.Pattern;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.seasar.framework.util.StringUtil;

import com.opensymphony.webwork.dispatcher.FilterDispatcher;

public class GusukuFilterDispatcher extends FilterDispatcher {

	private String ignorePath = "";
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
		ServletContext servletContext = filterConfig.getServletContext();
		String appName = "/" + servletContext.getRealPath("").substring(servletContext.getRealPath("").lastIndexOf(File.separator)+1);
		String path = ((HttpServletRequest)request).getRequestURI();
		path = path.substring(appName.length());

		if(!StringUtil.isEmpty(ignorePath)){
		Pattern pattern = Pattern.compile(ignorePath);
			if(pattern.matcher(path).matches()){
				String realPath = servletContext.getRealPath(path);
				InputStream in = new FileInputStream(realPath);
				OutputStream out = response.getOutputStream();
				int tmp = -1;
				while((tmp = in.read()) != -1){
					out.write(tmp);
				}
				in.close();
				out.flush();
				out.close();
				return;
			}
		}
		super.doFilter(request, response, filterChain);
	}
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		super.init(filterConfig);
		ignorePath = filterConfig.getInitParameter("ignorePath");
		if(StringUtil.isEmpty(ignorePath)){
			ignorePath = "";
		}
	}
	
	

}
