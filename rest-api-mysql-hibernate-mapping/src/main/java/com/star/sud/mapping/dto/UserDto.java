package com.star.sud.mapping.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class UserDto implements Serializable {

	// Static properties
	///////////////////
	private static final long serialVersionUID = 255034558169713817L;

	// private properties
	///////////////////
	private String userId;

	@NotNull(message = "userName is mandatory")
	private String userName;

	private String password;

	@NotNull(message = "email is mandatory")
	@Email(message = "email should be valid")
	private String email;

	@NotNull(message = "firstName is mandatory")
	@Size(min = 2, message = "firstName should be minimum of 2 char")
	private String firstName;

	@NotNull(message = "lastName is mandatory")
	@Size(min = 2, message = "lastName should be minimum of 2 char")
	private String lastName;

	private String gender;

	@NotNull(message = "dob is mandatory")
	private String dob;

	private List<AddressDetailsDto> addressDetails;

	private List<RoleDto> roles;

	// Constructor
	//////////////
	public UserDto() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<AddressDetailsDto> getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(List<AddressDetailsDto> addressDetails) {
		this.addressDetails = addressDetails;
	}

	public List<RoleDto> getRoles() {
		return roles;
	}

	public void setRoles(List<RoleDto> roles) {
		this.roles = roles;
	}

}
