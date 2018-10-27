package com.qa.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class RestClient
{
	
	// GET Call without header
	public CloseableHttpResponse getCall(String url) throws ClientProtocolException, IOException
	{
		CloseableHttpClient closablehttpclientObj = HttpClients.createDefault();
		HttpGet httpgetObj = new HttpGet(url);
		CloseableHttpResponse closablehttpresponseObj = closablehttpclientObj.execute(httpgetObj);
		
		return closablehttpresponseObj;
		
		
	}
	
	
	// GET Call with header
	public CloseableHttpResponse getCall(String url, HashMap<String, String> headermapObj) throws ClientProtocolException, IOException
	{
		CloseableHttpClient closablehttpclientObj = HttpClients.createDefault();
		HttpGet httpgetObj = new HttpGet(url);
		
		// it will add the headers in get call
		for(Map.Entry<String, String> entry : headermapObj.entrySet())
		{
			httpgetObj.addHeader(entry.getKey(), entry.getValue());
		}
		
		CloseableHttpResponse closablehttpresponseObj = closablehttpclientObj.execute(httpgetObj);
		
		return closablehttpresponseObj;
		
		
	}

}
