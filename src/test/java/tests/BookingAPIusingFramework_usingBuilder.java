package tests;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.response.Response;
import models.Booking;
import models.Booking.BookingDates;
import specification.ResponseSpec;
import utils.JsonUtils;

public class BookingAPIusingFramework_usingBuilder extends BaseTest
{
	int booking_id;

	@Test
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
	
	
}
