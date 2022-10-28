package com.star.sud.mapping.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.mapping.dto.UserDto;
import com.star.sud.mapping.repository.UserRespository;

@Service
public class UserService {

	@Autowired
	private UserRespository userRepository;

	public List<UserDto> getUsers() {
		List<UserDto> users = userRepository.findAll().stream()
				.map(user -> new UserDto(user.getId(), user.getEmail(), user.getFirstName(), user.getLastName()))
				.collect(Collectors.toList());
		return users;
	}

}
