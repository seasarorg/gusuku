package org.seasar.gusuku.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;
import org.seasar.gusuku.GusukuConstant;
import org.seasar.gusuku.dao.ReportDao;
import org.seasar.gusuku.entity.Report;

public class ReportServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pathInfo = request.getPathInfo();
		
		String[] params = pathInfo.split("/");
		
		if(params.length == 3){
			String key = params[1];
			String seq = params[2];
			
			S2Container container = SingletonS2ContainerFactory.getContainer();
			ReportDao reportDao = (ReportDao)container.getComponent(ReportDao.class);
			
			Report report = reportDao.findByKeyAndSeq(key,Long.valueOf(seq));
			if(report != null){
				if(request.getSession().getAttribute(GusukuConstant.AUTHENTICATE_KEY) != null){
					response.sendRedirect("/Gusuku/report_detail.html?id=" + report.getId());
					return;
				}else{
					response.sendRedirect("/Gusuku/index.html?report=" + report.getId());
					return;
				}
			}
		}
		
		response.sendRedirect("/Gusuku/index.html");
		return;
	}
	
	

}
