package com.star.sud.mapping.common;

public enum ErrorCode {

	EC001("E1001", "Missing required input"), EC002("E1002", "Failed Transaction from backend server"),
	EC003("E1003", "Invalid request input");

	private String code;
	private String description;

	private ErrorCode(String code, String description) {
		this.code = code;
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}