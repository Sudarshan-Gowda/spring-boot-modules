package com.star.sud.onetoone.bidirection_02.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.star.sud.onetoone.bidirection_02.entity.Course10;

@Repository
public interface Course10Repository extends JpaRepository<Course10, Integer> {

}
