package com.star.sud.common.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ErrorDetail implements Serializable {

	// Static Attributes
	///////////////////////
	private static final long serialVersionUID = 2467134566931778601L;

	// Attributes
	////////////////////
	private String code;

	private String message;

	private String details;

	private String severityLevel;

	private Date timeStamp;

	// Constructors
	////////////////

	public ErrorDetail(String code, String message, String severityLevel) {
		super();
		this.code = code;
		this.message = message;
		this.severityLevel = severityLevel;
	}

	public ErrorDetail(String code, String message, String details, String severityLevel) {
		super();
		this.code = code;
		this.message = message;
		this.details = details;
		this.severityLevel = severityLevel;
	}

	public ErrorDetail(String message, String details, Date timeStamp) {
		super();
		this.message = message;
		this.details = details;
		this.timeStamp = timeStamp;
	}

	public ErrorDetail() {
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSeverityLevel() {
		return severityLevel;
	}

	public void setSeverityLevel(String severityLevel) {
		this.severityLevel = severityLevel;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
