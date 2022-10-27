package com.star.sud.restservice.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class UserDto {

	private Integer userId;

	@NotNull(message = "userName is mandatory")
	@Size(min = 2, message = "userName should be minimum of 2 char")
	private String userName;

	@NotNull(message = "userEmail is mandatory")
	@Email(message = "userEmail  is not matching the critiria")
	private String userEmail;

	public UserDto() {
		super();
	}

	public UserDto(Integer userId, String userName, String userEmail) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.userEmail = userEmail;
	}

	public UserDto(Integer userId) {
		super();
		this.userId = userId;
	}

	public UserDto(String userName, String userEmail) {
		super();
		this.userName = userName;
		this.userEmail = userEmail;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

}
