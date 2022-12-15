package com.star.sud;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import com.star.sud.mapping.entity.City;
import com.star.sud.mapping.entity.Gender;
import com.star.sud.mapping.entity.Role;
import com.star.sud.mapping.entity.State;
import com.star.sud.mapping.repository.CityRepository;
import com.star.sud.mapping.repository.GenderRepository;
import com.star.sud.mapping.repository.RoleRepository;
import com.star.sud.mapping.repository.StateRepository;

@Component
public class DataLoader implements ApplicationRunner {

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	private StateRepository stateRepo;

	@Autowired
	private GenderRepository genderRepo;

	@Autowired
	private CityRepository cityRepo;

	static Map<String, String> genderMap = new HashMap<String, String>();
	static Map<String, String> roleMap = new HashMap<String, String>();
	static Map<String, String> stateMap = new HashMap<String, String>();
	static Map<String, String> cityMap = new HashMap<String, String>();

	private static String status = "ACTIVE";

	static {
		genderMap.put("MALE", "Male");
		genderMap.put("FEMALE", "Female");
		genderMap.put("OTHER", "other");
		roleMap.put("ADMIN", "Admin");
		roleMap.put("USER", "User");
		stateMap.put("KA", "Karnataka");
		stateMap.put("AD", "Andhra Pradesh");
		stateMap.put("MP", "Madhya Pradesh");
		cityMap.put("IND", "India");

	}

	public void run(ApplicationArguments args) {

		persist(genderRepo, Gender::getCode, code -> new Gender(code, genderMap.get(code), status), genderMap.keySet());

		persist(roleRepo, Role::getRoleName, name -> new Role(name, status), roleMap.keySet());

		persist(stateRepo, State::getCode, code -> new State(code, stateMap.get(code), status), stateMap.keySet());

		persist(cityRepo, City::getCode, code -> new City(code, cityMap.get(code), status), cityMap.keySet());

	}

	public <T, ID> void persist(JpaRepository<T, ID> repo, Function<T, String> mapperFunction,
			Function<String, T> exctractFunction, Set<String> newList) {

		List<String> existingGender = Optional.ofNullable(repo.findAll()).map(Collection::stream).orElse(Stream.empty())
				.map(mapperFunction).collect(Collectors.toList());
		newList.removeAll(existingGender);
		List<T> collect = newList.stream().map(exctractFunction).collect(Collectors.toList());
		repo.saveAll(collect);

	}
}