package com.brian.MatrixConversion.WebServer;

import org.eclipse.jetty.server.Server;

public class webserver {
	private Server webserver;
	private Thread server;
	
	private int port;
	
	webserver(int port){
		this.port = port;
	}
	
	public Server openWebServer() {
		server = new Thread(new Runnable() {
			public void run() {
				webserver = new Server(port);
				webserver.setHandler(new ResponseHandler() );
				try {
					webserver.start();
		           	webserver.join();
				}catch (Exception e) {
		               e.printStackTrace();
				}
			}
		});
		return webserver;
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
