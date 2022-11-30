package com.star.sud.mapping.controller;

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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.star.sud.mapping.dto.UserDto;
import com.star.sud.mapping.service.UserService;

@RestController
@RequestMapping("/mapping")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(value = "/users")
	public List<UserDto> getUsers() {
		return userService.getUsers();
	}

	@GetMapping(value = "/users/{userId}")
	public ResponseEntity<Object> getUsersById(@PathVariable Integer userId) throws Exception {
		return userService.getUsersById(userId);
	}

	@PostMapping(value = "/users")
	public ResponseEntity<Object> createUser(@RequestBody @Valid UserDto request) {
		return userService.createUser(request);
	}

	@PutMapping(value = "/users/{userId}")
	public ResponseEntity<Object> updateUser(@PathVariable("userId") String id, @RequestBody UserDto request) {
		return userService.updateUser(id, request);
	}

	@DeleteMapping(value = "/users/{userId}")
	public ResponseEntity<Object> deleteUser(@PathVariable("userId") Integer id) throws Exception {
		return userService.deleteUser(id);
	}

}
