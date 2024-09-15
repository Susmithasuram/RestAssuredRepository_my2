package models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Booking 
{
	private String firstname;
	private String lastname;
	private int totalprice;
	private boolean depositpaid;
	private BookingDates bookingdates;
	private String additionalneeds;
	
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@Builder
	public static class BookingDates
	{
		private String checkin;
		private String 	checkout;
	}
	
	

}
