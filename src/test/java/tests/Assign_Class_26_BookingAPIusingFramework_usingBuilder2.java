package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.response.Response;
import models.AuthToken_Booking;
import models.Booking;
import models.Booking.BookingDates;
import specification.ResponseSpec;
import utils.JsonUtils;

public class Assign_Class_26_BookingAPIusingFramework_usingBuilder2 extends BaseTest
{
	int booking_id;
	String token;
	
	@Test
	public void createToken()
	{
		AuthToken_Booking payLoad= AuthToken_Booking.builder().username("admin").password("password123").build();
		
		Response resp= sendRequest("post", "/auth",payLoad);
		System.out.println(resp.asPrettyString());
		token=JsonUtils.getString(resp, "token");
		System.out.println("token value is "+token);
	}
	
	

	@Test(priority = 1)
	public void createBooking()
	{
		Booking payLoad=Booking.builder()
				.firstname("susmitha")
				.lastname("Dodda")
				.totalprice(100)
				.depositpaid(true)
				.bookingdates(BookingDates.builder().checkin("2024-09-01").checkout("2024-09-02").build())
		        .additionalneeds("dinner")
		        .build();
		Response resp= sendRequest("post","/booking",payLoad);
		System.out.println(resp.asPrettyString());
		resp.then().spec(ResponseSpec.responseSpec());
		
		String name=JsonUtils.getString(resp,"booking.firstname");
		Assert.assertEquals(name, "susmitha");
		System.out.println("Assertion paased using json utils");
		
		booking_id=JsonUtils.getInteger(resp, "bookingid");
		
	}
	
	@Test(priority =2)
	public void updateBooking()
	{
		Map<String,Object> headersforUpdate=new HashMap<String, Object>();
		headersforUpdate.put("Content-Type", "application/json");
		headersforUpdate.put("Accept", "application/json");
		headersforUpdate.put("Cookie", "token="+token);
		
		Booking payLoad=Booking.builder()
				.firstname("Aarush")
				.lastname("suram")
				.totalprice(200)
				.depositpaid(true)
				.bookingdates(BookingDates.builder().checkin("2024-09-01").checkout("2024-09-02").build())
		        .additionalneeds("dinner")
		        .build();
		Response resp= sendRequest("put","/booking/"+booking_id,payLoad,headersforUpdate);
		resp.then().assertThat().spec(ResponseSpec.responseSpec());
		System.out.println(resp.asPrettyString());
		String updateName=JsonUtils.getString(resp, "firstname");
		System.out.println("updated name is "+updateName);
		Assert.assertEquals(updateName, "Aarush");
	}
	
	@Test(priority =3)
	public void deleteBooking() 
	{
		Map<String,Object> headersforDelete=new HashMap<String, Object>();
		headersforDelete.put("Content-Type", "application/json");
		headersforDelete.put("Authorization", "Basic YWRtaW46cGFzc3dvcmQxMjM");
		
		Response resp= sendRequest("delete", "/booking/"+booking_id,headersforDelete, null);
		resp.then().assertThat().spec(ResponseSpec.responseSpec_201());
		
	}
}
