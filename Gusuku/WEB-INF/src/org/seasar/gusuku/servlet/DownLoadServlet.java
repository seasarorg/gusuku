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

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.gusuku.GusukuConstant;
import org.seasar.gusuku.dao.ProjectDao;
import org.seasar.gusuku.entity.Account;
import org.seasar.gusuku.entity.Project;
import org.seasar.gusuku.util.PropertyUtil;

public class DownLoadServlet extends HttpServlet {

	private static final long serialVersionUID = 4486540540643907440L;
	
	private static final Log LOG = LogFactory.getLog(DownLoadServlet.class);

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String uri = request.getRequestURI();
		String[] pathInfo = getPathInfo(uri);
		String base = uri.substring(0,uri.indexOf("/download"));
		
		if(LOG.isDebugEnabled()){
			LOG.debug("RequestURI:"+request.getRequestURI());
			if(pathInfo != null){
				LOG.debug("Projectid = [" + pathInfo[0] + "]");
				LOG.debug("KIND = [" + pathInfo[1] + "]");
				LOG.debug("ID = [" + pathInfo[2] + "]");
				LOG.debug("FILENAME = [" + pathInfo[3] + "]");
			}else{
				LOG.debug("invalid Parameter [" + request.getRequestURI() + "]");
			}
		}
		
		if(pathInfo != null){
			//対象のファイルが自分が参加しているプロジェクトかどうかチェックする
			S2Container container = SingletonS2ContainerFactory.getContainer();
			ProjectDao projectDao = (ProjectDao)container.getComponent(ProjectDao.class);
			//TODO SessionManagerの利用
			Account account=(Account)request.getSession().getAttribute(GusukuConstant.AUTHENTICATE_KEY);
			if(account == null){
				response.sendRedirect(base+"/index.html");
				return;
			}
			Project project = projectDao.findByIdAndAccountid(new Long(pathInfo[0]),account.getId());
			if(project != null){
				try{
					BufferedOutputStream out = new BufferedOutputStream(response.getOutputStream());
					response.setContentType("application/octet-stream");
					//response.setHeader("Content-disposition", "attachment; filename=\""+URLEncoder.encode(pathInfo[1],"UTF-8")+"\"");
					String path= PropertyUtil.getProperty(GusukuConstant.UPLOAD_DIR_KEY);
					path = path + project.getKey() + File.separator + pathInfo[1] + File.separator + pathInfo[2];
					BufferedInputStream in = new BufferedInputStream(new FileInputStream(path));
					int data = 0;
					while((data=in.read()) != -1){
						out.write(data);
					}
					out.close();
					in.close();
				}catch(Exception e){
					LOG.error(e.getMessage());
				}
			}else{
				response.sendRedirect(base+"/entryProjectException.html");
				return;
			}
		}
	}
	
	/**
	 * ダウンロード要求のパスを分解する。<br>
	 * /download/<プロジェクトID>/<種別>/<ID>/<ファイル名><br>
	 * <ul>
	 *  <li>attach 通常添付ファイル</li>
	 *  <li>comment コメント添付ファイル</li>
	 * </ul>
	 * @param path
	 * @return String[]
	 */
	private String[] getPathInfo(String path){
		String info = path.substring(path.indexOf("download/") + "download/".length());
		String[] result = new String[4];
		StringTokenizer st = new StringTokenizer(info,"/");
		if(st.countTokens() == 4){
			result[0] = st.nextToken();
			result[1] = st.nextToken();
			result[2] = st.nextToken();
			result[3] = st.nextToken();
		}else{
			return null;
		}
		return result;
	}
	
	

}
