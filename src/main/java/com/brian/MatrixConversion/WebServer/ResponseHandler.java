package com.brian.MatrixConversion.WebServer;

import java.io.IOException;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ResponseHandler extends AbstractHandler {

    public ResponseHandler(){

    }

    @Override
    public void handle(String s, Request baseRequest, HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException, ServletException {
    	// TODO Auto-generated method stub
		response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET");

        response.setStatus(HttpServletResponse.SC_OK);

    	if(s.equals("")) {
    		
    	}else {
    		System.out.println("接收到莫名的資訊");
    		System.out.println("s = " + s);
    		System.out.println("httpServletRequest.toString() = " + httpServletRequest.toString());
    	}
        
    	baseRequest.setHandled(true);
    }
}
