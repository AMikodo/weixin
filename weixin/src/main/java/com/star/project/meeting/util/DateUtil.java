package com.star.project.meeting.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	public static String  getToday() {
		SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd");
		
		return dateFormat.format(new Date());
	}
	
	public static String  getYesterday() {
		SimpleDateFormat dateFormat=new SimpleDateFormat("MM/dd");
		Date date=new Date(System.currentTimeMillis()-1000*60*60*24);
		return dateFormat.format(date);
		
	}
}
