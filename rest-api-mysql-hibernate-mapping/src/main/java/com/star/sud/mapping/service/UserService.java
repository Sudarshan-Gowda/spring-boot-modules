//package com.star.sud.mapping.service;
//
//import java.net.URI;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.stereotype.Service;
//import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
//
//import com.star.sud.exception.ResourceNotFoundException;
//import com.star.sud.mapping.dto.UserDto;
//import com.star.sud.mapping.entity.User;
//import com.star.sud.mapping.repository.UserRespository;
//
//@Service
//public class UserService {
//
//	@Autowired
//	private UserRespository userRepository;
//
//	public List<UserDto> getUsers() {
//
//		List<UserDto> users = userRepository.findAll().stream().map(user -> copyUserObject(user))
//				.collect(Collectors.toList());
//		return users;
//	}
//
//	public ResponseEntity<Object> getUsersById(Integer userId) throws Exception {
//		User user = userRepository.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("No record found for userId: " + userId));
//
//		UserDto dto = new UserDto();
//		BeanUtils.copyProperties(user, dto);
//		return ResponseEntity.ok(dto);
//
//	}
//
//	private UserDto copyUserObject(User user) {
//		UserDto dto = new UserDto();
//		BeanUtils.copyProperties(user, dto);
//		return dto;
//	}
//
//	public ResponseEntity<Object> createUser(UserDto request) {
//		User entity = new User();
//		BeanUtils.copyProperties(request, entity);
//		User save = userRepository.save(entity);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/users/{id}")
//				.buildAndExpand(save.getUserId()).toUri();
//
//		ResponseEntity.created(location).build();
//
//		BeanUtils.copyProperties(save, request);
//
//		return ResponseEntity.created(location).body(request);
//	}
//
//	public ResponseEntity<Object> updateUser(String id, UserDto request) {
//		User entity = new User();
//		BeanUtils.copyProperties(request, entity);
//		entity.setUserId(id);
//		User save = userRepository.save(entity);
//		BeanUtils.copyProperties(save, request);
//		return ResponseEntity.ok(request);
//	}
//
//	public ResponseEntity<Object> deleteUser(Integer userId) throws Exception {
//		User user = userRepository.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("No record found for the userId: " + userId));
//		userRepository.delete(user);
//		return ResponseEntity.ok("Successfully Deleted");
//	}
//
//}
