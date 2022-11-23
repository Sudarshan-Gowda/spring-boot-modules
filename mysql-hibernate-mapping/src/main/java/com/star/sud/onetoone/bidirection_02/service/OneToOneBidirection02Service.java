package com.star.sud.onetoone.bidirection_02.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.onetoone.bidirection_02.entity.Course10;
import com.star.sud.onetoone.bidirection_02.entity.CourseMaterial10;
import com.star.sud.onetoone.bidirection_02.repository.Course10Repository;

@Service
public class OneToOneBidirection02Service {

	@Autowired
	private Course10Repository courseRepository;

	// Attaching CourseMaterial to Course will persist the data into both the table
	// when cascade is ON & owning entity is for Course -- but this is not best
	// option - just an alternative
	public void createCourseMaterial() {

		Random random = new Random(1);

		CourseMaterial10 material = new CourseMaterial10();
		material.setName("Course-Materail- " + random.nextInt());

		Course10 course = new Course10();
		course.setName("Course- " + random.nextInt());

		course.setMaterial(material);

		courseRepository.save(course);

	}

}
