package com.stockmarket.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class StockUtililty {
	
	
	 public static Date dateFormat(String date) throws ParseException{
		 
		 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
			Date date2 = formatter1.parse(date); 
			return date2;
	 }
	 
	 public static Date oneWeekBefore(String date) throws ParseException{
		 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");   
			Date date2 = formatter1.parse(date); 
			
			// get Calendar instance
			Calendar cal = Calendar.getInstance();
			cal.setTime(date2);

			// substract 7 days
			// If we give 7 there it will give 8 days back
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-6);

			// convert to date
			Date myDate = cal.getTime();
			
		 return myDate;
	 }
	 
	 public static Date daysBefore(String date,Integer period) throws ParseException{
		 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");   
			Date date2 = formatter1.parse(date); 
			
			// get Calendar instance
			Calendar cal = Calendar.getInstance();
			cal.setTime(date2);

			// substract 7 days
			// If we give 7 there it will give 8 days back
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-period);

			// convert to date
			Date myDate = cal.getTime();
			
		 return myDate;
	 }
	 
	 public static Date daysAfter(String date,Integer period) throws ParseException{
		 SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");   
			Date date2 = formatter1.parse(date); 
			
			// get Calendar instance
			Calendar cal = Calendar.getInstance();
			cal.setTime(date2);

			// substract 7 days
			// If we give 7 there it will give 8 days back
			cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)+period);

			// convert to date
			Date myDate = cal.getTime();
			
		 return myDate;
	 }
	 

}
