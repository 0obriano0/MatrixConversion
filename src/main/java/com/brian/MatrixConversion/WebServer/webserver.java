package com.brian.MatrixConversion.WebServer;

import org.eclipse.jetty.server.Server;

public class webserver {
	private Server webserver;
	private Thread server;
	private boolean isopen;
	
	private int port;
	
	/**
	 * 預設 port 8080
	 */
	public webserver() {
		this.port = 8080;
	}
	
	public webserver(int port){
		setport(port);
	}
	
	public void setport(int port) {
		this.port = port;
	}
	
	public void setport(String port) {
		this.port = Integer.parseInt(port);
	}
	
	public int getport() {
		return this.port;
	}
	
	public boolean openWebServer() {
		isopen = false;
		if(port <= 0)
			return false;
		System.out.println("啟用多執行續開啟...");
		server = new Thread(new Runnable() {
			public void run() {
				System.out.println("多執行續內...");
				webserver = new Server(port);
				webserver.setHandler(new ResponseHandler() );
				try {
					webserver.start();
					isopen = true;
					System.out.println("啟用成功");
		           	webserver.join();
				}catch (Exception e) {
					isopen = false;
		            e.printStackTrace();
				}
			}
		});
		server.start();
		return isopen;
	}
	
	public boolean openWebServer(int port) {
		setport(port);
		return openWebServer();
	}
	
	public boolean openWebServer(String port) {
		setport(port);
		return openWebServer();
	}
   
   /**
	 * 關閉伺服器端
    * @return
    */
	@SuppressWarnings("deprecation")
	public boolean closeWebServer() {
		try {
			webserver.stop();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		server.stop();
		return true;
   }
}
