package com.star.sud.restservice.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.star.sud.restservice.dto.UserDto;
import com.star.sud.restservice.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService service;

	@GetMapping(value = "/users")
	public List<UserDto> getUserList() {
		return service.getUsers();
	}

	@PostMapping(value = "/users")
	public ResponseEntity<?> createUser(@Valid @RequestBody UserDto request) {
		return service.createUser(request);
	}

	@PutMapping(value = "/users/{userId}")
	public ResponseEntity<?> updateUsers(@PathVariable Integer userId, @Valid @RequestBody UserDto request)
			throws Exception {
		return service.updateUser(userId, request);
	}

	@DeleteMapping(value = "/users/{userId}")
	public ResponseEntity<?> deleteUsers(@PathVariable Integer userId) throws Exception {
		return service.deleteUser(userId);

	}

}
