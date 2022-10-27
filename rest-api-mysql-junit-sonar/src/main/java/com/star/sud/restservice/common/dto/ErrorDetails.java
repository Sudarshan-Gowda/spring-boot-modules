package com.star.sud.restservice.common.dto;

import java.io.Serializable;
import java.util.Date;

public class ErrorDetails implements Serializable {

	private static final long serialVersionUID = 2467134566931778601L;

	private String message;

	private String details;

	private Date timeStamp;
	
	public ErrorDetails(String message, String details, Date timeStamp) {
		super();
		this.message = message;
		this.details = details;
		this.timeStamp = timeStamp;
	}

	public ErrorDetails() {
		super();
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
