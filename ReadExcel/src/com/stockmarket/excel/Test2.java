package com.stockmarket.excel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test2 {

	public static void main(String[] args) throws ParseException {
		
		  Calendar cal = Calendar.getInstance();
		  SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
		  Date date2 = formatter1.parse("2018-12-28"); 
		  cal.setTime(date2);
			
	      System.out.println("Current week of month is : " +cal.get(Calendar.WEEK_OF_MONTH));
	      System.out.println("Current week of year is : " +cal.get(Calendar.WEEK_OF_YEAR));
	     
	      	
	}
}
