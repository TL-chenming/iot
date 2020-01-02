package com.cht.tl.tts;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.net.URLEncoder;

import com.cht.tl.tts.Response;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TTSExample {
	
	boolean USE_PROXY = false;
	
	static final String API_KEY = "your api key";
	
	String Proxy_IP = "yourProxyIP";
	int Proxy_Port = 1234; // your proxy port
	
	public static void main(String[] args) {
		TTSExample tts = new TTSExample();
		tts.callSynthesisTest();
		tts.callSynthesisMultiTest();
		tts.callTwSynthesisTest();
	}
	
	public void callSynthesisTest() {
		try {
			long begin = System.currentTimeMillis();
			
			String text = "你是否要看民視新聞。";
			
			String outputFileName = "out.wav";
			
			URL u = new URL("http://iot.cht.com.tw/apis/CHTIoT/tts/v1/ch/synthesis?inputText=" + URLEncoder.encode(text, "UTF-8"));
			
			HttpURLConnection con = null;
			
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Proxy_IP, Proxy_Port));
			
			if (USE_PROXY)
				con = (HttpURLConnection) u.openConnection(proxy);
			else 
				con = (HttpURLConnection) u.openConnection();
			
			con.setRequestProperty("X-API-Key", API_KEY);
			con.setRequestMethod("GET");
					
			con.connect();

			int sc = con.getResponseCode();
			
			if (sc != 200) {
				throw new IOException(String.format("%d : %s", sc, con.getResponseMessage()));
			}
			
			InputStream is = con.getInputStream();
			StringBuilder sb = new StringBuilder();
	        String response = "";
	        try {
		        for (int c; (c = is.read()) >= 0;)
		            sb.append((char)c);
		        response = sb.toString();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        
	        //create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();
			
			//convert json string to object
			Response res = objectMapper.readValue(response, Response.class);
			
			long end = System.currentTimeMillis();
			
			System.out.printf("[%s] cost time: %,2d ms\n", text, (end - begin));
			System.out.println("status: " + res.getStatus() + ", file: " + res.getFile());
			System.out.println("===========================================");
			
			// save to local file
			String localFilePath = outputFileName;
	        
	        URL resultUrl = new URL(res.getFile());
	        if (USE_PROXY)
				con = (HttpURLConnection) resultUrl.openConnection(proxy);
			else 
				con = (HttpURLConnection) resultUrl.openConnection();
	        
	        con.setRequestProperty("X-API-Key", API_KEY);
	        con.setRequestMethod("GET");
			
			con.connect();
			sc = con.getResponseCode();
			
			if (sc != 200) {
				throw new IOException(String.format("%d : %s", sc, con.getResponseMessage()));
			}
			is = con.getInputStream();
			byte[] bytes = new byte[4096];
			int s;
			FileOutputStream fos = new FileOutputStream(localFilePath);
			while ((s = is.read(bytes)) > 0) {
				fos.write(bytes, 0, s);
			}
			fos.close();
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void callSynthesisMultiTest() {
		try {
			long begin = System.currentTimeMillis();
			
			String text = "你是否要看民視新聞。";
			
			String outputFileName = "out_female.wav";
			
			String speakerId = "lsj"; // default is lsj
			
			URL u = new URL("http://iot.cht.com.tw/apis/CHTIoT/tts/v1/ch/synthesis?inputText=" + URLEncoder.encode(text, "UTF-8") + "&speaker=" + speakerId);
			
			HttpURLConnection con = null;
			
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Proxy_IP, Proxy_Port));
			
			if (USE_PROXY)
				con = (HttpURLConnection) u.openConnection(proxy);
			else 
				con = (HttpURLConnection) u.openConnection();
			
			con.setRequestProperty("X-API-Key", API_KEY);
			con.setRequestMethod("Get");
					
			con.connect();

			int sc = con.getResponseCode();
			
			if (sc != 200) {
				throw new IOException(String.format("%d : %s", sc, con.getResponseMessage()));
			}
				
			InputStream is = con.getInputStream();
			StringBuilder sb = new StringBuilder();
	        String response = "";
	        try {
		        for (int c; (c = is.read()) >= 0;)
		            sb.append((char)c);
		        response = sb.toString();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        
	        //create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();
			
			//convert json string to object
			Response res = objectMapper.readValue(response, Response.class);
			
			long end = System.currentTimeMillis();
			
			System.out.printf("[%s] cost time: %,2d ms\n", text, (end - begin));
			System.out.println("status: " + res.getStatus() + ", file: " + res.getFile());
			System.out.println("===========================================");
			
			// save to local file
			String localFilePath = outputFileName;
	        
	        URL resultUrl = new URL(res.getFile());
	        if (USE_PROXY)
				con = (HttpURLConnection) resultUrl.openConnection(proxy);
			else 
				con = (HttpURLConnection) resultUrl.openConnection();
	        
	        con.setRequestProperty("X-API-Key", API_KEY);
	        con.setRequestMethod("GET");
			
			con.connect();
			sc = con.getResponseCode();
			
			if (sc != 200) {
				throw new IOException(String.format("%d : %s", sc, con.getResponseMessage()));
			}
			is = con.getInputStream();
			byte[] bytes = new byte[4096];
			int s;
			FileOutputStream fos = new FileOutputStream(localFilePath);
			while ((s = is.read(bytes)) > 0) {
				fos.write(bytes, 0, s);
			}
			fos.close();  
		} catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void callTwSynthesisTest() {
		try {
			long begin = System.currentTimeMillis();
			
			String text = "我要看民視新聞。";
			
			String outputFileName = "out_tw.wav";
			
			URL u = new URL("http://iot.cht.com.tw/apis/CHTIoT/tts/v1/tw/synthesis?inputText=" + URLEncoder.encode(text, "UTF-8"));
			
			HttpURLConnection con = null;
			
			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(Proxy_IP, Proxy_Port));
			
			if (USE_PROXY)
				con = (HttpURLConnection) u.openConnection(proxy);
			else 
				con = (HttpURLConnection) u.openConnection();
			
			con.setRequestProperty("X-API-Key", API_KEY);
			con.setRequestMethod("GET");
					
			con.connect();

			int sc = con.getResponseCode();
			
			if (sc != 200) {
				throw new IOException(String.format("%d : %s", sc, con.getResponseMessage()));
			}
			
			InputStream is = con.getInputStream();
			StringBuilder sb = new StringBuilder();
	        String response = "";
	        try {
		        for (int c; (c = is.read()) >= 0;)
		            sb.append((char)c);
		        response = sb.toString();
	        } catch (Exception e) {
	        	e.printStackTrace();
	        }
	        
	        //create ObjectMapper instance
			ObjectMapper objectMapper = new ObjectMapper();
			
			//convert json string to object
			Response res = objectMapper.readValue(response, Response.class);
			
			long end = System.currentTimeMillis();
			
			System.out.printf("[%s] cost time: %,2d ms\n", text, (end - begin));
			System.out.println("status: " + res.getStatus() + ", file: " + res.getFile());
			System.out.println("===========================================");
			
			// save to local file
			String localFilePath = outputFileName;
	        
	        URL resultUrl = new URL(res.getFile());
	        if (USE_PROXY)
				con = (HttpURLConnection) resultUrl.openConnection(proxy);
			else 
				con = (HttpURLConnection) resultUrl.openConnection();
	        
	        con.setRequestProperty("X-API-Key", API_KEY);
	        con.setRequestMethod("GET");
			
			con.connect();
			sc = con.getResponseCode();
			
			if (sc != 200) {
				throw new IOException(String.format("%d : %s", sc, con.getResponseMessage()));
			}
			is = con.getInputStream();
			byte[] bytes = new byte[4096];
			int s;
			FileOutputStream fos = new FileOutputStream(localFilePath);
			while ((s = is.read(bytes)) > 0) {
				fos.write(bytes, 0, s);
			}
			fos.close();  
		} catch (Exception e){
			e.printStackTrace();
		}
	}

}
