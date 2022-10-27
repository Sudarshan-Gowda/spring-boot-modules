package com.star.sud.restservice.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.sud.restservice.dto.UserDto;
import com.star.sud.restservice.entity.User;
import com.star.sud.restservice.repository.UserRepository;
import com.star.sud.restservice.service.UserService;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	UserRepository repo;

	@InjectMocks
	UserService service;

	@Autowired
	ObjectMapper mapper;

	@Test
	void getUsersTest() throws Exception {
		List<User> mockEntity = new ArrayList<>();
		mockEntity.add(new User(1, "user 1", "user1@gmail.com"));

		when(repo.findAll()).thenReturn(mockEntity);

		List<UserDto> users = service.getUsers();
		assertTrue(users.size() > 0);
		assertThat(users.get(0).getUserName()).isSameAs(mockEntity.get(0).getUserName());
		assertEquals(mockEntity.get(0).getUserEmail(), users.get(0).getUserEmail());

	}

	@Test
	void createUserTest() {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

		User user = new User(1, "user 1", "user1@gmail.com");
		when(repo.save(Mockito.any(User.class))).thenReturn(user);

		UserDto dto = new UserDto(1);
		BeanUtils.copyProperties(user, dto, "userId");
		ResponseEntity<?> createdUser = service.createUser(dto);
		List<String> list = createdUser.getHeaders().get(HttpHeaders.LOCATION);

		assertTrue(list.size() > 0);
		assertEquals(String.format("http://localhost/users/%s", user.getUserId()), list.get(0));
	}


	@Test
	void updateuserTest() throws Exception {
		User mockEntity = new User(1, "user 1", "user1@gmail.com");
		when(repo.findById(Mockito.anyInt())).thenReturn(Optional.of(mockEntity));
		when(repo.save(Mockito.any(User.class))).thenReturn(mockEntity);

		UserDto dto = new UserDto();
		BeanUtils.copyProperties(mockEntity, dto, "userId");
		ResponseEntity<?> updateUser = service.updateUser(1, dto);

		assertThat(updateUser.getStatusCode()).isSameAs(HttpStatus.OK);
		UserDto responseDto = (UserDto) updateUser.getBody();
		assertThat(responseDto.getUserEmail()).isSameAs(mockEntity.getUserEmail());
		assertEquals(responseDto.getUserId(), mockEntity.getUserId());

	}

	@Test
	void deleteUserTest() throws Exception {
		User mockEntity = new User(1, "user 1", "user1@gmail.com");

		when(repo.findById(Mockito.anyInt())).thenReturn(Optional.of(mockEntity));
		doNothing().when(repo).delete(mockEntity);

		ResponseEntity<?> deleteUser = service.deleteUser(1);
		assertThat(deleteUser.getStatusCode()).isSameAs(HttpStatus.OK);

	}

}
