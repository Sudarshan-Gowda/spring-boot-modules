package com.star.sud.onetomany.bidirection_cascading.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.onetomany.bidirection_cascading.entity.Course06;
import com.star.sud.onetomany.bidirection_cascading.entity.Teacher06;
import com.star.sud.onetomany.bidirection_cascading.repository.Course06Repository;

@Service
public class BidirectionService04 {

	@Autowired
	private Course06Repository courseRepo;

	public void createCourseWithTeacher() {

		Random rand = new Random();

		Teacher06 teacher = new Teacher06();
		teacher.setName("Teacher-01");
		// teacherRepo.save(teacher);

		Course06 course06 = new Course06();
		course06.setName("Java- " + rand.nextInt());
		course06.setTeacher(teacher);
		courseRepo.save(course06);

	}

}
