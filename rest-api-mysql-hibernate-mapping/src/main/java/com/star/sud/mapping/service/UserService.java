package com.star.sud.mapping.service;

import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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
import com.star.sud.mapping.util.GenerateResponse;
import com.star.sud.mapping.util.LoginUtil;

@Service
@Transactional
public class UserService {

	@Autowired
	private UserRespository userRepository;

	public ResponseEntity<Object> createUser(UserDto request) throws Exception {

		User entity = createUserFromDto(request);
		User save = userRepository.save(entity);

		UserDto response = createUserDtoFromUser(save);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/users/{id}")
				.buildAndExpand(save.getUserId()).toUri();

		return ResponseEntity.created(location).body(GenerateResponse.getSuccessResponse(response));
	}

	public ResponseEntity<Object> getUsers() throws Exception {

		List<UserDto> users = userRepository.findAll().stream().map(user -> createUserDtoFromUser(user))
				.collect(Collectors.toList());

		return ResponseEntity.ok(GenerateResponse.getSuccessResponse(users));
	}

	public ResponseEntity<Object> getUsersById(String userId) throws Exception {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for userId: " + userId));

		return ResponseEntity.ok(GenerateResponse.getSuccessResponse(createUserDtoFromUser(user)));

	}

	public ResponseEntity<Object> updateUser(String userId, UserDto request) throws Exception {

		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for the userId: " + userId));

		User entity = updateUserFromDto(request, user);
		User entityUpdated = userRepository.saveAndFlush(entity);
		UserDto response = createUserDtoFromUser(entityUpdated);
		return ResponseEntity.ok(GenerateResponse.getSuccessResponse(response));
	}

	public ResponseEntity<Object> deleteUser(String userId) throws Exception {
		User user = userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("No record found for the userId: " + userId));
		userRepository.delete(user);
		return ResponseEntity.ok(GenerateResponse.getSuccessResponse("Successfully Deleted"));
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
			Set<AddressDetails> address = request.getAddressDetails().stream().map(a -> createAddress(a, entity))
					.collect(Collectors.toSet());
			entity.setAddressDetails(address);
		}

		if (request.getRoles() != null && request.getRoles().size() > 0) {
			Set<Role> roles = request.getRoles().stream().map(r -> createRoles(r)).collect(Collectors.toSet());
			entity.setRoles(roles);
		}

		return entity;
	}

	private User updateUserFromDto(UserDto request, User entity) throws Exception {

		BeanUtils.copyProperties(request, entity, "userName", "email", "password", "userId");

		if (request.getGender() != null)
			entity.setGender(new Gender(request.getGender()));

		if (request.getDob() != null)
			entity.setDob(DateUtil.getDateFromString(request.getDob()));

		// TODO: Get the user details from the security context
		entity.setUpdatedBy(LoginUtil.loggedInUser());
		entity.setUpdatedDate(DateUtil.getCurrentDate());

		if (request.getAddressDetails() != null && request.getAddressDetails().size() > 0) {

			Set<AddressDetails> address = request.getAddressDetails().stream().map(a -> createAddress(a, entity))
					.collect(Collectors.toSet());
			entity.setAddressDetails(address);
		}

		if (request.getRoles() != null && request.getRoles().size() > 0) {
			Set<Role> roles = request.getRoles().stream().map(r -> createRoles(r)).collect(Collectors.toSet());
			entity.setRoles(roles);
		}

		return entity;
	}

	private UserDto createUserDtoFromUser(User user) {
		UserDto userDto = new UserDto();
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

	private AddressDetails createAddress(AddressDetailsDto addressDto, User user) {
		AddressDetails address = null;
		if (user.getAddressDetails() != null) {
			Optional<AddressDetails> addressOption = user.getAddressDetails().stream()
					.filter(a -> a.getAddressType().equalsIgnoreCase(addressDto.getAddressType())).findFirst();
			if (addressOption.isPresent()) {
				address = addressOption.get();
				address.setUpdatedBy(LoginUtil.loggedInUser());
				address.setUpdatedDate(DateUtil.getCurrentDate());
			}
		}

		if (address == null) {
			address = new AddressDetails();
			address.setCreatedDate(DateUtil.getCurrentDate());
			address.setCreatedBy(LoginUtil.loggedInUser());
		}

		address.setCity(new City(addressDto.getCity()));
		address.setState(new State(addressDto.getState()));
		BeanUtils.copyProperties(addressDto, address, "addressId", "createdDate", "createdBy", "updatedBy");
		address.setUser(user);
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
