package tests;

import org.testng.annotations.Test;

import base.BaseTest;
import io.restassured.response.Response;
import models.Booking;
import models.Booking.BookingDates;
import specification.ResponseSpec;

public class BookingAPIusingFramework_usingPogo extends BaseTest
{

	@Test
	public void createBooking()
	{
		Booking payLoad=new Booking("susmitha", "Dodda", 10, true,new BookingDates("2024-09-01","2024-09-02"), "dinner");
		
		Response resp= sendRequest("post","/booking",payLoad);
		System.out.println(resp.asPrettyString());
		resp.then().spec(ResponseSpec.responseSpec());
		
	}
}
