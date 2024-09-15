package specification;

import config.ConfigManager;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestSpec
{
	public static RequestSpecification requestspec()
	{
	 RequestSpecBuilder req=new RequestSpecBuilder()
		.setBaseUri("https://restful-booker.herokuapp.com")
		.setContentType(ContentType.JSON);
		//.build();
	  //return req;
	 
	 if (ConfigManager.getProperty("request_logs").equals("true")) 
	 {
		return req.log(LogDetail.ALL).build();
	 }	
	 else
	 {
		return req.build();
	 }
	 
	}

}
