package com.star.sud.mapping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "ADDRESS_DETAILS", uniqueConstraints = {
		@UniqueConstraint(name = "AddressTypeAndUser", columnNames = { "ADDRESS_TYPE", "USER_ID" }) })
public class AddressDetails extends AbstractEntity {

	private static final long serialVersionUID = 1439995038770745823L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ADDRESS_ID", length = 30)
	private Integer addressId;

	@Column(name = "ADDRESS_TYPE", nullable = false, length = 10)
	private String addressType;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "USER_ID")
	private User user;

	@Column(name = "ADDRESS_LINE_01", nullable = false)
	private String addressLine01;

	@Column(name = "ADDRESS_LINE_02")
	private String addressLine02;

	@Column(name = "ADDRESS_LINE_03")
	private String addressLine03;

	@OneToOne
	@JoinColumn(name = "CITY", referencedColumnName = "CODE", nullable = false)
	private City city;

	@OneToOne
	@JoinColumn(name = "STATE", referencedColumnName = "CODE", nullable = false)
	private State state;

	@Column(name = "PIN_CODE", nullable = false)
	private String pinCode;

	@Column(name = "LAND_MARK")
	private String landMark;

	@Column(name = "PRIMARY_CONTACT_NUMBER")
	private String primaryContactNumber;

	@Column(name = "SECONDARY_CONTACT_NUMBER")
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}

}
