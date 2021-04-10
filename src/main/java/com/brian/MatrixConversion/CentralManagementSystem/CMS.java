package com.brian.MatrixConversion.CentralManagementSystem;

import com.brian.MatrixConversion.WebServer.webserver;

public class CMS {
	private static webserver webserver = new webserver();
	
	public static webserver getwebserver() {
		return webserver;
	}
}
