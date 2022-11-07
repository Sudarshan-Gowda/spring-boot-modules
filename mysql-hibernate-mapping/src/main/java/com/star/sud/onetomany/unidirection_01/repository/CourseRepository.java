package com.star.sud.onetomany.unidirection_01.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.star.sud.onetomany.unidirection_01.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {

}
