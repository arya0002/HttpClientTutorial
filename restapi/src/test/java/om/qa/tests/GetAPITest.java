package om.qa.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.apache.http.Header;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.client.RestClient;

public class GetAPITest extends BaseTest
{
	BaseTest baseTestObj;
	String URL; 
	String serviceURL;
	String mainURL;
	RestClient restClientObj;
	CloseableHttpResponse closablehttpresponseObj;
	
	
	@BeforeMethod
	public void  setUP()
	{
		baseTestObj = new BaseTest();
		URL  = prop.getProperty("URL");
		serviceURL = prop.getProperty("serviceURL");
		
		mainURL = URL+serviceURL;
	}
	
	
	@Test
	public void getAPITestWithoutHeaders() throws ClientProtocolException, IOException
	{
		
		
		restClientObj = new RestClient();
		closablehttpresponseObj = restClientObj.getCall(mainURL);
		
		
		// Status code verification from response
		int statusCode = closablehttpresponseObj.getStatusLine().getStatusCode();
		System.out.println("Status code is -----------> "+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200," Status code is not as expected");
		
		
		//Reading JSON Response
		String response = EntityUtils.toString(closablehttpresponseObj.getEntity(),"UTF-8");
		System.out.println("Response is --------> "+response);
		
        // Reading data from JSON
		JSONObject jsonObjectObj = new JSONObject(response);
		int total = jsonObjectObj.getInt("total");
		System.out.println("total value is -----> "+ total);
		Assert.assertEquals(total, 12,"Total value is not as expected");
		
		//Reading data from JSON Array
		JSONArray jsonArrayObj = jsonObjectObj.getJSONArray("data");
		int id = jsonArrayObj.getJSONObject(0).getInt("id");
		System.out.println("id is --------> "+id);
		Assert.assertEquals(id, 1,"id is not as expected");
		
				
		//Reading Headers
		Header[] headerArray = closablehttpresponseObj.getAllHeaders();
		HashMap<String, String> allHeadersObj = new HashMap<String, String>();
		for(Header header : headerArray)
		{
			allHeadersObj.put(header.getName(), header.getValue());
		}
				
		System.out.println("Headers are ---------> "+ allHeadersObj);
						
	}
	
	@Test
	public void getAPITestWithHeaders() throws ClientProtocolException, IOException
	{
		// Creating the map of headers which needs to be passed along with the get call
		HashMap<String, String> headerMapObj = new HashMap<String, String>();
		headerMapObj.put("Content-Type", "application/json");
		
		// Creating the object of RestClient class and calling the appropriate method
		restClientObj = new RestClient();
		closablehttpresponseObj = restClientObj.getCall(mainURL, headerMapObj);
		
		
		
		// Status code verification from response
		int statusCode = closablehttpresponseObj.getStatusLine().getStatusCode();
		System.out.println("Status code is -----------> "+statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_200," Status code is not as expected");
		
		
		//Reading JSON Response
		String response = EntityUtils.toString(closablehttpresponseObj.getEntity(),"UTF-8");
		System.out.println("Response is --------> "+response);
		
        // Reading data from JSON
		JSONObject jsonObjectObj = new JSONObject(response);
		int total = jsonObjectObj.getInt("total");
		System.out.println("total value is -----> "+ total);
		Assert.assertEquals(total, 12,"Total value is not as expected");
		
		//Reading data from JSON Array
		JSONArray jsonArrayObj = jsonObjectObj.getJSONArray("data");
		int id = jsonArrayObj.getJSONObject(0).getInt("id");
		System.out.println("id is --------> "+id);
		Assert.assertEquals(id, 1,"id is not as expected");
		
				
		//Reading Headers
		Header[] headerArray = closablehttpresponseObj.getAllHeaders();
		HashMap<String, String> allHeadersObj = new HashMap<String, String>();
		for(Header header : headerArray)
		{
			allHeadersObj.put(header.getName(), header.getValue());
		}
				
		System.out.println("Headers are ---------> "+ allHeadersObj);
						
	}


}
