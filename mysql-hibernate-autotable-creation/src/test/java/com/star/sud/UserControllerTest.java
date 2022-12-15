package com.star.sud;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.net.URI;
import java.text.ParseException;
import java.util.ArrayList;
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
import com.star.sud.mapping.controller.UserController;
import com.star.sud.mapping.dto.AddressDetailsDto;
import com.star.sud.mapping.dto.UserDto;
import com.star.sud.mapping.service.UserService;
import com.star.sud.mapping.util.GenerateResponse;

@ExtendWith(SpringExtension.class)
@WebMvcTest(UserController.class)
class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService service;

	@Autowired
	private ObjectMapper mapper;

	@Test
	void getUserList() throws Exception {
		List<UserDto> dto = mockDto();

		ResponseEntity<Object> ok = ResponseEntity.ok(GenerateResponse.getSuccessResponse(dto));

		when(service.getUsers()).thenReturn(ok);

		this.mockMvc.perform(get("/mapping/users")).andExpect(status().isOk())//
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))//
				.andExpect(jsonPath("$.data.[0].userId").value("USER00001"))//
				.andExpect(jsonPath("$.data.[0].email").value("sudarshan@gmail.com"));
	}

	@Test
	void createUserTest() throws Exception {

		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

		UserDto userDto = mockDto().get(0);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/mapping/users/{id}")
				.buildAndExpand(userDto.getUserId()).toUri();

		when(service.createUser(Mockito.any(UserDto.class))).thenReturn(ResponseEntity.created(location).build());

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/mapping/users")
				.contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(userDto));

		MockHttpServletResponse response = mockMvc.perform(requestBuilder).andExpect(status().isCreated()).andReturn()
				.getResponse();
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals("http://localhost/mapping/users/" + userDto.getUserId(), response.getHeader(HttpHeaders.LOCATION));

	}

	@Test
	void updateUserTest() throws Exception {
		UserDto userDto = mockDto().get(0);

		ResponseEntity<?> ok = new ResponseEntity<>(userDto, HttpStatus.OK);

		Mockito.<ResponseEntity<?>>when(service.updateUser(Mockito.anyString(), Mockito.any(UserDto.class)))
				.thenReturn(ok);

		MockHttpServletResponse response = mockMvc
				.perform(put("/mapping/users/{userId}", 1).contentType(MediaType.APPLICATION_JSON_VALUE)
						.content(mapper.writeValueAsString(userDto)))//
				.andExpect(status().isOk()).andReturn().getResponse();

		assertEquals(response.getContentAsString(), mapper.writeValueAsString(userDto));
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	void deleteUserTest() throws Exception {

		when(service.deleteUser(Mockito.anyString()))
				.thenReturn(ResponseEntity.ok(GenerateResponse.getSuccessResponse("Successfully Deleted")));
		mockMvc.perform(delete("/mapping/users/{userId}", "USER00001")).andExpect(status().isOk());

	}

	private List<UserDto> mockDto() throws ParseException {
		List<UserDto> mockEntity = new ArrayList<>();
		UserDto user = new UserDto("USER00001", "sudarshan", "password", "sudarshan@gmail.com", "Sudarshan", "NP",
				"1990-01-01");

		List<AddressDetailsDto> addressDetails = new ArrayList<>();
		AddressDetailsDto adDetails = new AddressDetailsDto(1, "HOME", "USER00001", "01, Park square", "Whitefield",
				"Bangalore");
		addressDetails.add(adDetails);
		user.setAddressDetails(addressDetails);
		mockEntity.add(user);
		return mockEntity;
	}

}