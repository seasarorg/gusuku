package org.seasar.gusuku.listener;

import java.sql.SQLException;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.h2.tools.Server;


public class H2Listener implements ServletContextListener {
	
	private Server tcpServer;
	private Server webServer;

	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println("Starting H2 database ... ");
		try {
			//String[] params = new String[]{"-baseDir","sample"};
			/*
			String[] params = new String[]{"-baseDir","E:/EclipseProjects/Gusuku/WEB-INF/data","-tcpAllowOthers","true"};
			tcpServer = Server.createTcpServer(params);
			tcpServer.start();
			webServer = Server.createWebServer(params);
			webServer.start();
			System.out.println("done");
			System.out.println(tcpServer.getURL());
			*/
			
			Server.main(new String[]{"-tcp","-baseDir","E:/EclipseProjects/Gusuku/WEB-INF/data"});
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("Shutting down H2 database ... ");
		try{
		//Server.main(new String[]{"-tcpShutdown","tcp://localhost:9092"});
		Server.shutdownTcpServer("tcp://localhost:9092","",true);
		}catch(SQLException e){
			e.printStackTrace();
		}
		/*
		if(tcpServer != null && tcpServer.isRunning()){
			tcpServer.stop();
			
			try{
			Server.shutdownTcpServer(tcpServer.getURL(),"",true);
			System.out.println("done");
			}catch(SQLException e){
				e.printStackTrace();
			}
			
		}else{
			System.out.println("not running");
		}
		if(webServer != null && webServer.isRunning()){
			webServer.stop();
		}else{
			System.out.println("not running");
		}
		*/
	}

}
