package com.star.sud.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.star.sud.mapping.entity.City;

public interface CityRepository extends JpaRepository<City, String> {

}
