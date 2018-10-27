package om.qa.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.base.BaseTest;
import com.qa.client.RestClient;
import com.qa.data.UsersData;



public class PostAPITest extends BaseTest
{
	BaseTest baseTestObj; 
	String url;
	String serviceURL;
	String mainURL;
	RestClient restClientObj;
	CloseableHttpResponse closablehttpresponseObj;
	
	@BeforeMethod
	public void setUP()
	{
		baseTestObj = new BaseTest();
		url = prop.getProperty("URL");
		serviceURL = prop.getProperty("serviceURL");

		mainURL = url+serviceURL;
	}
	
	
	@Test
	public void postAPITestWithHeaders() throws JsonGenerationException, JsonMappingException, IOException
	{
		restClientObj = new RestClient();
		
		// it will create the headermap which need to pass with the post call
		HashMap<String, String> headersMapObj = new HashMap<String, String>();
		{
			headersMapObj.put("Content-Type", "application/json");
		}
		
		//JACKSON API : for converting the java object into the JSON object
		ObjectMapper objectMapperObj = new ObjectMapper();
		UsersData usersDataObj = new UsersData("morpheus", "leader");    // Expected user object
		
		// now we have to convert the usersdata.java object to users.json file
		objectMapperObj.writeValue(new File(System.getProperty("user.dir")+"/src/main/java/com/qa/data/users.json"), usersDataObj);
		
		// java Object to json in string
		String usersJasonString = objectMapperObj.writeValueAsString(usersDataObj);
		System.out.println("Users json string is ----> "+usersJasonString);
		
		// now call the postCall method
		closablehttpresponseObj = restClientObj.postCall(mainURL, usersJasonString , headersMapObj);
		 
		
		//validate response
		//1. status code
		int statusCode = closablehttpresponseObj.getStatusLine().getStatusCode();
		System.out.println("Status code is ---------> " + statusCode);
		Assert.assertEquals(statusCode, RESPONSE_STATUS_CODE_201," Status code is not as expected");
		
		//2. JSON String
		String response = EntityUtils.toString(closablehttpresponseObj.getEntity(), "UTF-8");
		System.out.println("Response is --------> "+response);
		
		//convert the string in JSON
		JSONObject jasonObjectObj = new JSONObject(response);
		System.out.println("the respose from api is ---> "+ jasonObjectObj);
		
		
		//3. JSON to java object
	    UsersData userResponseObj = objectMapperObj.readValue(response,UsersData.class);  //Actual user object, that we are getting
	    System.out.println(userResponseObj);
	    
	    Assert.assertTrue(userResponseObj.getName().equals(usersDataObj.getName()));
	    Assert.assertTrue(userResponseObj.getJob().equals(usersDataObj.getJob()));
	    
	    System.out.println(userResponseObj.getId());
	    System.out.println(userResponseObj.getCreatedAt());
		
	}
	

}
