package com.star.sud.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.star.sud.mapping.entity.Gender;

public interface GenderRepository extends JpaRepository<Gender, String> {

}
