package tests;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.mozilla.javascript.ast.NewExpression;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.google.common.annotations.VisibleForTesting;

import base.BaseTest;
import config.ConfigManager;
import io.restassured.response.Response;
import models.Booking;
import models.Booking.BookingDates;
import models.Booking_wrapper;
import specification.ResponseSpec;
import utils.JsonUtils;

public class Class_26_End_to_frameworkTest extends BaseTest
{
	int booking_id;

	@Test(priority = 1)
	public void createBooking()
	{
		System.out.println(new Date());
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
		
		Booking_wrapper booking_response= JsonUtils.deserialize(resp,Booking_wrapper.class);
		booking_id=booking_response.getBookingid();
		String firtsName=booking_response.getBooking().getFirstname();
		
		Assert.assertNotNull(booking_id);
		Assert.assertTrue(firtsName.equals("susmitha"));
		
	}
	
	@Test(priority = 2,dependsOnMethods = "createBooking")
	
	public void updateBooking()
	{
		Booking payLoad=Booking.builder()
				.firstname("Aarush")
				.lastname("Suram")
				.totalprice(100)
				.depositpaid(true)
				.bookingdates(BookingDates.builder().checkin("2024-09-01").checkout("2024-09-02").build())
		        .additionalneeds("dinner")
		        .build();
		String token=ConfigManager.getProperty("token");
		System.out.println("token value is "+token);
		Map<String,Object> headersforUpdate=new HashMap<String, Object>();
		headersforUpdate.put("Content-Type", "application/json");
		headersforUpdate.put("Accept", "application/json");
		headersforUpdate.put("Cookie", "token="+ConfigManager.getProperty("token"));
		
	Response resp=sendRequest("put", "/booking/"+booking_id, payLoad, headersforUpdate);
	Booking updateResponse=JsonUtils.deserialize(resp, Booking.class);
	String firstName= updateResponse.getFirstname();
	Assert.assertEquals(firstName,"Aarush");
	System.err.println(firstName);
		
	}
	
	@Test(priority = 3,dependsOnMethods ="createBooking")
	public void deleteBooking()
	{
		Map<String,Object> headersforDelete=new HashMap<String, Object>();
		headersforDelete.put("Content-Type", "application/json");
		headersforDelete.put("Cookie", "token="+ConfigManager.getProperty("token"));
		
		Response resp= sendRequest("delete","/booking/"+booking_id, headersforDelete);
		
		resp.then().assertThat().spec(ResponseSpec.responseSpec_201());
		
		System.out.println("*********calling get request to verify whether user is successfully deleted**********");
		Response getResponse= sendRequest("get", "/booking/"+booking_id, null);
		System.out.println(getResponse.getStatusCode());
		getResponse.then().assertThat().spec(ResponseSpec.responseSpec_404());
		
	}
}
