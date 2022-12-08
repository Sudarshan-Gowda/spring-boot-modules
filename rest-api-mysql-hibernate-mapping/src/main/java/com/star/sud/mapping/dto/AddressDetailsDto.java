package com.star.sud.mapping.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AddressDetailsDto extends AbstractDto {

	private static final long serialVersionUID = 5864862800045264927L;

	private Integer addressId;

	private String addressType;

	private String userId;

	private String addressLine01;

	private String addressLine02;

	private String addressLine03;

	private String city;

	private String state;

	private String pinCode;

	private String landMark;

	private String primaryContactNumber;

	private String secondaryContactNumber;

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		this.addressId = addressId;
	}

	public String getAddressType() {
		return addressType;
	}

	public void setAddressType(String addressType) {
		this.addressType = addressType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAddressLine01() {
		return addressLine01;
	}

	public void setAddressLine01(String addressLine01) {
		this.addressLine01 = addressLine01;
	}

	public String getAddressLine02() {
		return addressLine02;
	}

	public void setAddressLine02(String addressLine02) {
		this.addressLine02 = addressLine02;
	}

	public String getAddressLine03() {
		return addressLine03;
	}

	public void setAddressLine03(String addressLine03) {
		this.addressLine03 = addressLine03;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getLandMark() {
		return landMark;
	}

	public void setLandMark(String landMark) {
		this.landMark = landMark;
	}

	public String getPrimaryContactNumber() {
		return primaryContactNumber;
	}

	public void setPrimaryContactNumber(String primaryContactNumber) {
		this.primaryContactNumber = primaryContactNumber;
	}

	public String getSecondaryContactNumber() {
		return secondaryContactNumber;
	}

	public void setSecondaryContactNumber(String secondaryContactNumber) {
		this.secondaryContactNumber = secondaryContactNumber;
	}

}
