package com.star.sud.mapping.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDto implements Serializable {

	// Static properties
	///////////////////
	private static final long serialVersionUID = 255034558169713817L;

	// private properties
	///////////////////
	private Integer id;

	@JsonIgnore
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

	// Constructor
	//////////////
	public UserDto() {
		super();
	}

	public UserDto(Integer id, String email, String firstName, String lastName) {
		super();
		this.id = id;
		this.email = email;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	// Setter & Getters
	//////////////////////
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

}
