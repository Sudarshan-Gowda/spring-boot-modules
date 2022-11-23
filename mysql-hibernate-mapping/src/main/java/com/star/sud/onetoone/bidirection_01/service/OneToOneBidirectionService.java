package com.star.sud.onetoone.bidirection_01.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.onetoone.bidirection_01.entity.Course09;
import com.star.sud.onetoone.bidirection_01.entity.CourseMaterial09;
import com.star.sud.onetoone.bidirection_01.repository.CourseMaterial09Repository;

@Service
public class OneToOneBidirectionService {

//	@Autowired
//	private Course09Repository courseRepository;

	@Autowired
	private CourseMaterial09Repository courseMaterialRepository;

	// Attaching Course to CourseMaterial will persist the data into both the table
	// when cascade is ON & owning entity is for CourseMaterial
	public void createCourseMaterial() {

		Random random = new Random(1);

		Course09 course = new Course09();
		course.setName("Course- " + random.nextInt());

		CourseMaterial09 material = new CourseMaterial09();
		material.setName("Course-Materail- " + random.nextInt());
		material.setCourse(course);

		courseMaterialRepository.save(material);

	}

}
