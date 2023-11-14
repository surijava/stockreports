package com.stockmarket.excel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Test {

	public static void main(String[] args) throws Exception {
		 
		
		SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");  
		Date date2 = formatter1.parse("2018-03-08"); 
		
		// get Calendar instance
		Calendar cal = Calendar.getInstance();
		cal.setTime(date2);

		// substract 7 days
		// If we give 7 there it will give 8 days back
		cal.set(Calendar.DAY_OF_MONTH, cal.get(Calendar.DAY_OF_MONTH)-6);

		// convert to date
		Date myDate = cal.getTime();
		
		 java.sql.Date insertdate = new java.sql.Date(myDate.getTime()); 
		 System.out.println(insertdate);
		System.out.println(myDate);
		
		SimpleDateFormat formatter10 = new SimpleDateFormat("yyyyMMdd");
		System.out.println(formatter10.format(new Date()));
		
		Date d1 = new Date();
	      Calendar cl = Calendar. getInstance();
	      cl.setTime(d1);
	      
	      System.out.println("today is " + cl.WEEK_OF_YEAR+ " week of the year");
	      System.out.println("today is a "+cl.DAY_OF_MONTH + "month of the year");
	      System.out.println("today is a "+cl.WEEK_OF_MONTH +"week of the month");
		
		
	}

}
