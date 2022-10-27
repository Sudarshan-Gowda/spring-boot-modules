package com.star.sud.restservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.star.sud.restservice.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

}
