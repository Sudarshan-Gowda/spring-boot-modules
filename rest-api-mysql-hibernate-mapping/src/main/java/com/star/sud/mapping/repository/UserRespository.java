package com.star.sud.mapping.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.star.sud.mapping.entity.User;

public interface UserRespository extends JpaRepository<User, Integer> {

}
