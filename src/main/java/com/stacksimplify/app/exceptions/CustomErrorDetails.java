package com.stacksimplify.app.exceptions;

import java.util.Date;

//Simple custom error details bean
public class CustomErrorDetails {
	
	private Date date1;
	private String message;
	private String erromedetails;
	
	
	public CustomErrorDetails(Date date1, String message, String erromedetails) {
		super();
		this.date1 = date1;
		this.message = message;
		this.erromedetails = erromedetails;
	}


//	public CustomErrorDetails(Date date2, String message2, String message3) {
//		// TODO Auto-generated constructor stub
//	}


	public Date getDate() {
		return date1;
	}


	public String getMessage() {
		return message;
	}


	public String getErromedetails() {
		return erromedetails;
	}
	
	
	

}
