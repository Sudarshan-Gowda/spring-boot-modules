package com.star.sud.restservice.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.star.sud.restservice.dto.UserDto;
import com.star.sud.restservice.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;

	@Autowired
	private ObjectMapper mapper;

	List<UserDto> mockUsersDto = Arrays.asList(new UserDto("user 1", "user1@gmail.com"));

	@Test
	void getUserList() throws Exception {
		List<UserDto> dto = new ArrayList<>();
		dto.add(new UserDto("user 1", "user1@gmail.com"));

		when(service.getUsers()).thenReturn(dto);

		this.mockMvc.perform(get("/users")).andExpect(status().isOk())//
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))//
				.andExpect(jsonPath("$.[0].userName").value("user 1"))//
				.andExpect(jsonPath("$.[0].userEmail").value("user1@gmail.com"));
	}

	@Test
	void createUserTest() throws Exception {

		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

		UserDto userDto = new UserDto(1, "user 1", "user1@gmail.com");

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/users/{id}")
				.buildAndExpand(userDto.getUserId()).toUri();

		when(service.createUser(Mockito.any(UserDto.class))).thenReturn(ResponseEntity.created(location).build());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/users").contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(userDto));

		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andReturn()
				.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals("http://localhost/users/1", response.getHeader(HttpHeaders.LOCATION));

	}

	@Test
	void updateUserTest() throws Exception {
		UserDto userDto = new UserDto(1, "user 1", "user1@gmail.com");

		ResponseEntity<?> ok = new ResponseEntity<>(userDto, HttpStatus.OK);

		Mockito.<ResponseEntity<?>>when(service.updateUser(Mockito.anyInt(), Mockito.any(UserDto.class)))
				.thenReturn(ok);

		MockHttpServletResponse response = mockMvc
				.perform(put("/users/{userId}", 1).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(userDto)))//
				.andExpect(status().isOk()).andReturn().getResponse();
		
		assertEquals(response.getContentAsString(), mapper.writeValueAsString(userDto));
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	void deleteUserTest() throws Exception {

		when(service.deleteUser(Mockito.anyInt())).thenReturn(new ResponseEntity<>(HttpStatus.OK));
		mockMvc.perform(delete("/users/{userId}", 1)).andExpect(status().isOk());

	}

}