package dataProvider;

import org.testng.annotations.DataProvider;

public class CustomDataProvider
{
	
	@DataProvider(name="bookingDetails")
	public static Object[][] getBookingData()
	{
		Object[][] arr=new Object[2][7];
		
		  arr[0][0]="susmitha";
		  arr[0][1]="Dodda";
		  arr[0][2]=100;
		  arr[0][3]=true;
		  arr[0][4]="2024-07-01";
		  arr[0][5]="2024-07-10";
		  arr[0][6]="Lunch";
		  
		  arr[1][0]="Vrithitha";
		  arr[1][1]="Suram";
		  arr[1][2]=100;
		  arr[1][3]=true;
		  arr[1][4]="2024-07-01";
		  arr[1][5]="2024-07-10";
		  arr[1][6]="Lunch";
		  
		  arr[2][0]="Aarush";
		  arr[2][1]="Suram";
		  arr[2][2]=100;
		  arr[2][3]=true;
		  arr[2][4]="2024-07-01";
		  arr[2][5]="2024-07-10";
		  arr[2][6]="Dinner";
		
		
		
		return arr;
		
	}

}
