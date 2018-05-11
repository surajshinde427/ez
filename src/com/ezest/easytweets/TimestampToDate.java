package com.ezest.easytweets;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TimestampToDate {
	
	 String returnDate =null;
	
	public TimestampToDate(Timestamp timestamp) {
		Date date = new Date(timestamp.getTime());
		String dateformat=new SimpleDateFormat("dd-MM-yyyy").format(date);
		this.returnDate=dateformat;
	}
	
	
	public String getDateTime() {
		return returnDate;
	}


	public void setDateTime(String date) {
		this.returnDate = date;
	}



}
