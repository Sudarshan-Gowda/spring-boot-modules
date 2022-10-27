package com.star.sud.restservice.service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.star.sud.exception.ResourceNotFoundException;
import com.star.sud.restservice.dto.UserDto;
import com.star.sud.restservice.entity.User;
import com.star.sud.restservice.repository.UserRepository;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRepository repository;

	public List<UserDto> getUsers() {

		return repository.findAll().stream()
				.map(user -> new UserDto(user.getUserId(), user.getUserName(), user.getUserEmail()))
				.collect(Collectors.toList());

	}

	public ResponseEntity<?> createUser(UserDto request) {

		User user = new User();
		BeanUtils.copyProperties(request, user);
		User createUser = repository.save(user);
		BeanUtils.copyProperties(createUser, request);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/users/{id}")
				.buildAndExpand(createUser.getUserId()).toUri();
		return ResponseEntity.created(location).build();

	}

	public ResponseEntity<?> updateUser(Integer userId, UserDto request) throws Exception {

		User user = repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for the userId: " + userId));

		BeanUtils.copyProperties(request, user, "userId");
		User updatedUser = repository.save(user);
		BeanUtils.copyProperties(updatedUser, request);
		return new ResponseEntity<>(request, HttpStatus.OK);
	}

	public ResponseEntity<?> deleteUser(Integer userId) throws Exception {
		User user = repository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for the userId: " + userId));
		repository.delete(user);
		return new ResponseEntity<>(HttpStatus.OK);

	}

}
