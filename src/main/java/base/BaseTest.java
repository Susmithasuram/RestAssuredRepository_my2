package base;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import config.ConfigManager;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import specification.RequestSpec;

public class BaseTest 
{
	RequestSpecification req;
	@BeforeClass
	public void setUp()
	{
		req= RequestSpec.requestspec();
		generateandStoreToken();
	}
	
	public void generateandStoreToken()
	{
		boolean regenerate=Boolean.parseBoolean(ConfigManager.getProperty("regenerate"));
		String currentToken=ConfigManager.getProperty("token");
		if(regenerate||currentToken==null||regenerate==true||currentToken.equals("null")||currentToken.isEmpty())
		{
			//RequestSpecification spec=RequestSpec.requestspec().basePath("/auth");
			Response resp= given().spec(req).body("{\r\n"
					+ "    \"username\" : \"admin\",\r\n"
					+ "    \"password\" : \"password123\"\r\n"
					+ "}")
			.post("/auth");
			String token_value= resp.jsonPath().getString("token");
			System.out.println(resp.asPrettyString());
			if (token_value!=null)
			{
				ConfigManager.updatePropertyFile("token", token_value);
			}
			else 
			
			{
				System.out.println("failed to generate new token");
			}
			
		}
		else
		{
			System.out.println("Token is already present in config file");
		}
	}
	
	public Response sendRequest(String method,String resourceName,Object payLoad,
			Map<String,Object> headersForApi,Map<String,Object> queryParametersForApi,
			String authToken)
	{
		req= given().spec(req);
		if (headersForApi!=null) 
		{
			req.headers(headersForApi);
		}
		if (queryParametersForApi!=null) 
		{
			req.queryParams(queryParametersForApi);
		}
		if (authToken!=null) 
		{
			req.auth().preemptive().oauth2(authToken);
		}
		if (payLoad!=null) 
		{
		    req.body(payLoad);
		}
		
		switch (method.toUpperCase()) {
		case "GET":
			return req.get(resourceName);
			
		case "POST":
			return req.post(resourceName);
		case "PUT":
			return req.put(resourceName);
		case "DELETE":
			return req.delete(resourceName);

		default:
			throw new IllegalArgumentException("Please use valid method name");
		}
	}
	
	public Response sendRequest(String method,String resourceName,Object payLoad) 
	{
		return sendRequest(method, resourceName, payLoad, null, null,null);
	}
	public Response sendRequest(String method,String resourceName,Object payLoad
			,Map<String,Object> headersForApi) 
	{
		return sendRequest(method, resourceName, payLoad,headersForApi, null,null);
	}
	public Response sendRequest(String method,String resourceName,Object payLoad,
			Map<String,Object> headersForApi,Map<String,Object> queryParametersForApi
			)
	{
		return sendRequest(method, resourceName, payLoad,headersForApi,queryParametersForApi,null);
	}
	public Response sendRequest(String method,String resourceName,
			Map<String,Object> headersForApi,Map<String,Object> queryParametersForApi
			)
	{
		return sendRequest(method, resourceName,headersForApi,queryParametersForApi,null);
	}
	

}
