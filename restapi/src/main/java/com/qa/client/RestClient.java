package com.qa.client;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
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
	
	
	// POST call with headers
	public CloseableHttpResponse postCall(String url, String entityString, HashMap<String, String> headerMaoObj) throws ClientProtocolException, IOException
	{
		// it will create a default client
		CloseableHttpClient closablehttpclientObj = HttpClients.createDefault();
		// it will generate a post call
		HttpPost httpPostObj = new HttpPost(url);
		// it will add the payload to the http post call
		httpPostObj.setEntity(new StringEntity(entityString));
		
		// it will add header to the http post call
		for(Map.Entry<String, String> header : headerMaoObj.entrySet())
		{
			httpPostObj.addHeader(header.getKey(),header.getValue());
		}
		
		// it will execute the http post call
		CloseableHttpResponse closablehttpresponseObj = closablehttpclientObj.execute(httpPostObj);
		
		return closablehttpresponseObj;
		
	}

}
