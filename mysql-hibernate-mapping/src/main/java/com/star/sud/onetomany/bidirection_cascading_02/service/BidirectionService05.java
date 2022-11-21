package com.star.sud.onetomany.bidirection_cascading_02.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.onetomany.bidirection_cascading_02.entity.Course07;
import com.star.sud.onetomany.bidirection_cascading_02.entity.Teacher07;
import com.star.sud.onetomany.bidirection_cascading_02.repository.Teacher07Repository;

@Service
public class BidirectionService05 {

	@Autowired
	private Teacher07Repository teacherRepo;

	public void createTeacherWithCourse() {

		Random rand = new Random();

		List<Course07> courses = new ArrayList<>();
		Course07 course = new Course07();
		course.setName("Java- " + rand.nextInt());
		Course07 course2 = new Course07();
		course2.setName("Java- " + rand.nextInt());
		courses.add(course2);

		Teacher07 teacher = new Teacher07();
		teacher.setName("Teacher-01");
		teacher.setCourses(courses);

		teacherRepo.save(teacher);

	}

}
