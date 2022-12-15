package com.star.sud;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

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
import com.star.sud.common.dto.ResponseDTO;
import com.star.sud.mapping.dto.UserDto;
import com.star.sud.mapping.entity.AddressDetails;
import com.star.sud.mapping.entity.User;
import com.star.sud.mapping.repository.UserRespository;
import com.star.sud.mapping.service.UserService;
import com.star.sud.mapping.util.DateUtil;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

	@Mock
	UserRespository repo;

	@InjectMocks
	UserService service;

	@Autowired
	ObjectMapper mapper;

	@SuppressWarnings("unchecked")
	@Test
	void getUsersTest() throws Exception {

		List<User> mockEntity = mockEntity();

		when(repo.findAll()).thenReturn(mockEntity);

		ResponseEntity<?> responseEntity = service.getUsers();

		assertThat(responseEntity.getStatusCode()).isSameAs(HttpStatus.OK);
		ResponseDTO<List<UserDto>> responseDto = (ResponseDTO<List<UserDto>>) responseEntity.getBody();
		List<UserDto> users = responseDto.getData();
		assertEquals(users.size(), mockEntity.size());
		assertThat(users.get(0).getEmail()).isSameAs(mockEntity.get(0).getEmail());
		assertEquals(users.get(0).getUserId(), users.get(0).getUserId());
	}

	@Test
	void createUserTest() throws Exception {
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(new MockHttpServletRequest()));

		User user = mockEntity().get(0);
		when(repo.save(Mockito.any(User.class))).thenReturn(user);

		UserDto dto = new UserDto();
		BeanUtils.copyProperties(user, dto);
		ResponseEntity<?> createdUser = service.createUser(dto);
		List<String> list = createdUser.getHeaders().get(HttpHeaders.LOCATION);

		assertTrue(list.size() > 0);
		assertEquals(String.format("http://localhost/mapping/users/%s", user.getUserId()), list.get(0));
	}

	@SuppressWarnings("unchecked")
	@Test
	void updateuserTest() throws Exception {
		User mockEntity = mockEntity().get(0);
		when(repo.findById(Mockito.anyString())).thenReturn(Optional.of(mockEntity));
		when(repo.save(Mockito.any(User.class))).thenReturn(mockEntity);

		UserDto dto = new UserDto();
		BeanUtils.copyProperties(mockEntity, dto);
		ResponseEntity<?> updateUser = service.updateUser(dto.getUserId(), dto);

		assertThat(updateUser.getStatusCode()).isSameAs(HttpStatus.OK);
		ResponseDTO<UserDto> responseDto = (ResponseDTO<UserDto>) updateUser.getBody();
		UserDto userDto = responseDto.getData();
		assertThat(userDto.getEmail()).isSameAs(mockEntity.getEmail());
		assertEquals(userDto.getUserId(), mockEntity.getUserId());

	}

	@Test
	void deleteUserTest() throws Exception {
		User mockEntity = mockEntity().get(0);

		when(repo.findById(Mockito.anyString())).thenReturn(Optional.of(mockEntity));
		doNothing().when(repo).delete(mockEntity);

		ResponseEntity<?> deleteUser = service.deleteUser(mockEntity.getUserId());
		assertThat(deleteUser.getStatusCode()).isSameAs(HttpStatus.OK);

	}

	private List<User> mockEntity() throws ParseException {
		List<User> mockEntity = new ArrayList<>();
		User user = new User("USER00001", "sudarshan", "password", "sudarshan@gmail.com", "Sudarshan", "NP",
				DateUtil.getDateFromString("1990-01-01"));

		Set<AddressDetails> addressDetails = new HashSet<>();
		AddressDetails adDetails = new AddressDetails(1, "HOME", user, "01, Park square", "Whitefield", "Bangalore");
		addressDetails.add(adDetails);
		user.setAddressDetails(addressDetails);
		mockEntity.add(user);
		return mockEntity;
	}

}
