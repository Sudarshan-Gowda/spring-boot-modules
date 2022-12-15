package com.star.sud.mapping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.star.sud.mapping.entity.User;

public interface UserRespository extends JpaRepository<User, String> {

	@Query("SELECT count(*) FROM User where userName = :userName or email = :email")
	public int validateUserContrainsts(@Param("userName") String userName, @Param("email") String email);

	public List<User> findByUserNameOrEmail(String userName, String email);

}
