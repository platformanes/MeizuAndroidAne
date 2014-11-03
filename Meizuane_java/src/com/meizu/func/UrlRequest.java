package com.meizu.func;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import android.util.Log;

public class UrlRequest {

	public static String request(String url){
		try{
			HttpURLConnection con =  (HttpURLConnection) new URL(url).openConnection();
	        con.setConnectTimeout(30000);
	        con.setReadTimeout(3000);
	        con.setDoInput(true);
	        con.setRequestMethod("GET");
	        int responseCode = con.getResponseCode();
	        if(responseCode == 200){
	        	 InputStream stream = con.getInputStream();
	        	 BufferedReader br = new BufferedReader(new InputStreamReader(stream, "UTF-8"));
                 StringBuffer buf = new StringBuffer();
                 String line;
                 while (null != (line = br.readLine())) {
                     buf.append(line).append("\n");
                 }
                 stream.close();
                 con.disconnect();
                 return buf.toString();
	        }else{
	        	throw new Exception("server response code : " + responseCode);
	        }
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
}
