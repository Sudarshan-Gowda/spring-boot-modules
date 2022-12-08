package com.star.sud.mapping.service;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.star.sud.exception.ResourceNotFoundException;
import com.star.sud.mapping.dto.AddressDetailsDto;
import com.star.sud.mapping.dto.RoleDto;
import com.star.sud.mapping.dto.UserDto;
import com.star.sud.mapping.entity.AddressDetails;
import com.star.sud.mapping.entity.City;
import com.star.sud.mapping.entity.Gender;
import com.star.sud.mapping.entity.Role;
import com.star.sud.mapping.entity.State;
import com.star.sud.mapping.entity.User;
import com.star.sud.mapping.repository.UserRespository;
import com.star.sud.mapping.util.DateUtil;
import com.star.sud.mapping.util.LoginUtil;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRespository userRepository;

	public ResponseEntity<Object> createUser(UserDto request) throws Exception {

		User entity = createUserFromDto(request);
		User save = userRepository.save(entity);

		UserDto userDto = createUserDtoFromUser(save, request);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/users/{id}")
				.buildAndExpand(save.getUserId()).toUri();

		return ResponseEntity.created(location).body(userDto);
	}

	public List<UserDto> getUsers() {

		List<UserDto> users = userRepository.findAll().stream().map(user -> copyUserObject(user))
				.collect(Collectors.toList());
		return users;
	}

	public ResponseEntity<Object> getUsersById(String userId) throws Exception {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for userId: " + userId));

		UserDto dto = new UserDto();
		BeanUtils.copyProperties(user, dto);
		return ResponseEntity.ok(dto);

	}

	public ResponseEntity<Object> updateUser(String id, UserDto request) {
		User entity = new User();
		BeanUtils.copyProperties(request, entity);
		entity.setUserId(id);
		User save = userRepository.save(entity);
		BeanUtils.copyProperties(save, request);
		return ResponseEntity.ok(request);
	}

	public ResponseEntity<Object> deleteUser(String userId) throws Exception {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for the userId: " + userId));
		userRepository.delete(user);
		return ResponseEntity.ok("Successfully Deleted");
	}

	// Helper Methods
	///////////////////
	private User createUserFromDto(UserDto request) throws Exception {
		User entity = new User();

		BeanUtils.copyProperties(request, entity);

		if (request.getGender() != null)
			entity.setGender(new Gender(request.getGender()));

		if (request.getDob() != null)
			entity.setDob(DateUtil.getDateFromString(request.getDob()));

		// TODO: Get the user details from the security context
		entity.setCreatedBy(LoginUtil.loggedInUser());

		if (request.getAddressDetails() != null && request.getAddressDetails().size() > 0) {
			List<AddressDetails> address = request.getAddressDetails().stream().map(a -> createAddress(a))
					.collect(Collectors.toList());
			entity.setAddressDetails(address);
		}

		if (request.getRoles() != null && request.getRoles().size() > 0) {
			List<Role> roles = request.getRoles().stream().map(r -> createRoles(r)).collect(Collectors.toList());
			entity.setRoles(roles);
		}

		return entity;
	}

	private UserDto createUserDtoFromUser(User user, UserDto userDto) throws Exception {
		BeanUtils.copyProperties(user, userDto);
		if (user.getDob() != null)
			userDto.setDob(DateUtil.getDateInStringFmInputDate(user.getDob()));

		if (user.getAddressDetails() != null) {
			List<AddressDetailsDto> address = user.getAddressDetails().stream().map(a -> copyAddress(a))
					.collect(Collectors.toList());
			userDto.setAddressDetails(address);
		}

		if (user.getRoles() != null) {
			List<RoleDto> roles = user.getRoles().stream().map(r -> copyRoles(r)).collect(Collectors.toList());
			userDto.setRoles(roles);
		}

		return userDto;
	}

	private UserDto copyUserObject(User user) {
		user.setGender(new Gender());
		UserDto dto = new UserDto();
		BeanUtils.copyProperties(user, dto);
		return dto;
	}

	private AddressDetails createAddress(AddressDetailsDto addressDto) {
		AddressDetails address = new AddressDetails();
		address.setCity(new City(addressDto.getCity()));
		address.setState(new State(addressDto.getState()));
		BeanUtils.copyProperties(addressDto, address);
		address.setCreatedBy(LoginUtil.loggedInUser());
		return address;
	}

	private Role createRoles(RoleDto dto) {
		Role role = new Role();
		BeanUtils.copyProperties(dto, role);
		return role;
	}

	private AddressDetailsDto copyAddress(AddressDetails address) {
		AddressDetailsDto dto = new AddressDetailsDto();
		BeanUtils.copyProperties(address, dto);
		return dto;
	}

	private RoleDto copyRoles(Role role) {
		RoleDto dto = new RoleDto();
		BeanUtils.copyProperties(role, dto);
		return dto;
	}
}
