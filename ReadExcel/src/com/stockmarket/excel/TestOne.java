package com.stockmarket.excel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class TestOne {

	public static void main(String[] args) throws Exception {
        
        
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
             
            // Last week 
           Calendar LastWeekcalendar = Calendar.getInstance();
           LastWeekcalendar.setTime(new Date());
           Date lastWeekDate = firstDayOfLastWeek(LastWeekcalendar).getTime();
   
     System.out.println("Lastweek Day : " + firstDayOfLastWeek(LastWeekcalendar).getTime());
     System.out.println(format1.format(firstDayOfLastWeek(LastWeekcalendar).getTime()));
     
     
     //Coming week 
     
     Calendar futurecal=new GregorianCalendar();
     futurecal.add(Calendar.DATE, 90);
     Date futureDate =futurecal.getTime();
     
     //Diff 
     
     long diffInMillies = Math.abs(futureDate.getTime() - lastWeekDate.getTime());
     long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
     System.out.println(diff);
     
     
     // Display
     
     String[] date = format1.format(firstDayOfLastWeek(LastWeekcalendar).getTime()).split("-");
     for (int i = 1; i <= diff; i++) {
          Calendar c = Calendar.getInstance();
          c.setTime(lastWeekDate);
          c.add(Calendar.DATE, i);  // number of days to add
          String displayDate = format1.format(c.getTime());  // dt is now the new date
          
          System.out.println(displayDate+"----display"+c.get(c.WEEK_OF_YEAR)+"-- year week"+c.get(c.DAY_OF_WEEK));
           }
  
 
    }
    
    public static Calendar firstDayOfLastWeek(Calendar c)
 {
     c = (Calendar) c.clone();
     // last week
     c.add(Calendar.WEEK_OF_YEAR, -1);
     // first day
     c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek());
     return c;
 }


}
