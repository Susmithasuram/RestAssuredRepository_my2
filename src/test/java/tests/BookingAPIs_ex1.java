package tests;

import org.mozilla.javascript.ast.NewExpression;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.Date;

public class BookingAPIs_ex1 
{
	@Test
	public void createBooking()
	{
		baseURI="https://restful-booker.herokuapp.com";
		Response resp= given().contentType(ContentType.JSON)
		.body("{\r\n"
				+ "    \"firstname\" : \"Jim\",\r\n"
				+ "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}")
		.post("/booking")
		.then()
		.statusCode(200)
		.extract()
		.response();
		System.out.println(resp.asPrettyString());
		
		
		
	}

}
