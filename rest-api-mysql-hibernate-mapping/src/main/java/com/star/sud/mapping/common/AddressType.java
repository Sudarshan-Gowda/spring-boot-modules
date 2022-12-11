package com.star.sud.mapping.common;

public enum AddressType {

	HOME("HOME", "Home"), OFFICE("OFFICE", "Office");

	private AddressType(String code, String description) {
		this.code = code;
		this.description = description;
	}

	private String code;

	private String description;

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
