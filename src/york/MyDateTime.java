package york;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class MyDateTime{

//HH converts hour in 24 hours format (0-23), day calculation

public static long calculateTimeDiff(String createTime,String resTime)
{

	SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
   //  dateStart = "Fri, 1 Feb 2008 23:18:52 +0000";	//only for testing
	// dateStop = "Fri, 8 Feb 2008 19:12:54 +0000";
	 Date d1 = null;
	 Date d2 = null;
	 long diff=0;
	 try {
		d1 = format.parse(createTime);
		d2 = format.parse(resTime);
	
		//in milliseconds
		diff = d2.getTime() - d1.getTime();
		//return diff/1000;
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);
	
	/*	System.out.print(diffDays + " days, ");
		System.out.print(diffHours + " hours, ");
		System.out.print(diffMinutes + " minutes, ");
		System.out.print(diffSeconds + " seconds.");
	*/ //uncomment to see bug resolution time in days, hours, mins 	
	} 	catch (Exception e) {
			e.printStackTrace();
	}
	 System.out.println("Reolution time in Seconds:"+ diff / 1000 );
	return (diff/1000);
}
}