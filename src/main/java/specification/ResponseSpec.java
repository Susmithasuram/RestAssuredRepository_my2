package specification;

import config.ConfigManager;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

public class ResponseSpec 
{
	public static ResponseSpecification responseSpec()
	{
		ResponseSpecBuilder respBuilder= new ResponseSpecBuilder()
		//.expectContentType(ContentType.JSON)
	    .expectStatusCode(200);
	    if (ConfigManager.getProperty("response_logs").equals("true"))
	    {
			respBuilder.log(LogDetail.ALL);
		}
	    return respBuilder.build();
		
	}

	public static ResponseSpecification responseSpec_201()
	{
		ResponseSpecBuilder respBuilder= new ResponseSpecBuilder()
		//.expectContentType(ContentType.JSON)
	    .expectStatusCode(201);
		 if (ConfigManager.getProperty("response_logs").equals("true"))
		    {
				respBuilder.log(LogDetail.ALL);
			}
		    return respBuilder.build();
	    
	}
	
	public static ResponseSpecification responseSpec_404()
	{
		ResponseSpecBuilder respBuilder= new ResponseSpecBuilder()
		//.expectContentType(ContentType.JSON)
	    .expectStatusCode(404);
		 if (ConfigManager.getProperty("response_logs").equals("true"))
		    {
				respBuilder.log(LogDetail.ALL);
			}
		    return respBuilder.build();
	    	
	}
}
