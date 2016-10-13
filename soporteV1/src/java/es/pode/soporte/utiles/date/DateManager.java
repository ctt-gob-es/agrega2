package es.pode.soporte.utiles.date;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

public class DateManager {

	public static Calendar dateToCalendar(Date fecha) {
     
		 Calendar cal = Calendar.getInstance();
		 cal.setTime(fecha);
		 
		 return cal;
	}
	
	public static Calendar timestampToCalendar(Timestamp fecha) {
	     
         Calendar cal = Calendar.getInstance();
         cal.setTimeInMillis(fecha.getTime());
		 
		 return cal;
	}
}
