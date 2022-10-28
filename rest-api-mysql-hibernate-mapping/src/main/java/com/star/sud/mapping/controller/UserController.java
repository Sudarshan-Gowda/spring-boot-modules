package com.star.sud.mapping.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.star.sud.mapping.dto.UserDto;
import com.star.sud.mapping.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/mapping/users")
	public List<UserDto> getUsers() {
		return userService.getUsers();
	}

}
