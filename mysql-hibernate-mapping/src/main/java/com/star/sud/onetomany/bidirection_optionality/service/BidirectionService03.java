package com.star.sud.onetomany.bidirection_optionality.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.onetomany.bidirection_optionality.entity.Course05;
import com.star.sud.onetomany.bidirection_optionality.entity.Teacher05;
import com.star.sud.onetomany.bidirection_optionality.repository.Course05Repository;
import com.star.sud.onetomany.bidirection_optionality.repository.Teacher05Repository;

@Service
public class BidirectionService03 {

	@Autowired
	private Teacher05Repository teacherRepo;

	@Autowired
	private Course05Repository courseRepo;

	// after making optional as false, we can't save the course without attaching
	// teacher
	public void createCourseWithoutTeacher() {

		Random rand = new Random();

		Course05 course05 = new Course05();
		course05.setName("Java- " + rand.nextInt());
		courseRepo.save(course05);

	}

	public void createCourseWithTeacher() {

		Random rand = new Random();

		Teacher05 teacher = new Teacher05();
		teacher.setName("Teacher-01");
		teacherRepo.save(teacher);

		Course05 course05 = new Course05();
		course05.setName("Java- " + rand.nextInt());
		course05.setTeacher(teacher);
		courseRepo.save(course05);

	}

}
