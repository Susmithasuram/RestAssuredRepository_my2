package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.response.Response;
import specification.ResponseSpec;

public class BookingAPIusingFramework extends BaseTest
{

	@Test
	public void createBooking()
	{
		String payLoad="{\r\n"
				+ "    \"firstname\" : \"Jim\",\r\n"
				+ "    \"lastname\" : \"Brown\",\r\n"
				+ "    \"totalprice\" : 111,\r\n"
				+ "    \"depositpaid\" : true,\r\n"
				+ "    \"bookingdates\" : {\r\n"
				+ "        \"checkin\" : \"2018-01-01\",\r\n"
				+ "        \"checkout\" : \"2019-01-01\"\r\n"
				+ "    },\r\n"
				+ "    \"additionalneeds\" : \"Breakfast\"\r\n"
				+ "}";
		
		Response resp= sendRequest("post","/booking",payLoad);
		System.out.println(resp.asPrettyString());
		resp.then().spec(ResponseSpec.responseSpec());
		
	}
}
