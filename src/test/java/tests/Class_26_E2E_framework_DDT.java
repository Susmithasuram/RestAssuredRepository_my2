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
import dataProvider.CustomDataProvider;
import io.restassured.response.Response;
import models.Booking;
import models.Booking.BookingDates;
import models.Booking_wrapper;
import specification.ResponseSpec;
import utils.JsonUtils;

public class Class_26_E2E_framework_DDT extends BaseTest
{
	int booking_id;

	@Test(dataProvider = "bookingDetails",dataProviderClass =CustomDataProvider.class)
	public void createBooking(String firstName,String lastName,int price,boolean status,String checkIn,String checkOut,String additionalNeeds)
	{
		System.out.println(new Date());
		Booking payLoad=Booking.builder()
				.firstname(firstName)
				.lastname(lastName)
				.totalprice(price)
				.depositpaid(status)
				.bookingdates(BookingDates.builder().checkin(checkIn).checkout(checkOut).build())
		        .additionalneeds(additionalNeeds)
		        .build();
		Response resp= sendRequest("post","/booking",payLoad);
		System.out.println(resp.asPrettyString());
		resp.then().spec(ResponseSpec.responseSpec());
		
		Booking_wrapper booking_response= JsonUtils.deserialize(resp,Booking_wrapper.class);
		booking_id=booking_response.getBookingid();
		//String firtsName=booking_response.getBooking().getFirstname();
		
		Assert.assertNotNull(booking_id);
		System.out.println("bookingId is "+booking_id);
		//Assert.assertTrue(firtsName.equals("susmitha"));
		
	}
	
	
	
}
