package com.stockmarket.excel;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.TimeUnit;

public class BeforeAfterDate {

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
        futurecal.add(Calendar.DATE, 60);
        Date futureDate =futurecal.getTime();
        
        //Diff 
        
        long diffInMillies = Math.abs(futureDate.getTime() - lastWeekDate.getTime());
        long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        System.out.println(diff);
        
        
        // Display
        
        String[] date = format1.format(firstDayOfLastWeek(LastWeekcalendar).getTime()).split("-");
        for (int i = 0; i <= diff; i++) {
        	 Calendar displaycal=new GregorianCalendar(Integer.valueOf(date[0]),
        			 Integer.valueOf(date[1])-1,Integer.valueOf(date[2]));
        	 displaycal.add(Calendar.DATE, i);
             Date displayDate =displaycal.getTime();
            
            
             Calendar c = Calendar.getInstance();
             c.setTime(displayDate);
             int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
             
             SimpleDateFormat simpleDateformat = new SimpleDateFormat("E");
             
             System.out.println(format1.format(displayDate.getTime())+"----display"+dayOfWeek+"---"+simpleDateformat.format(displayDate));
             
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
