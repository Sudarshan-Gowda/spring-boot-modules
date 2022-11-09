package com.star.sud.onetomany.bidirection_el_loading.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.onetomany.bidirection_el_loading.entity.Course04;
import com.star.sud.onetomany.bidirection_el_loading.entity.Teacher04;
import com.star.sud.onetomany.bidirection_el_loading.repository.Course04Repository;
import com.star.sud.onetomany.bidirection_el_loading.repository.Teacher04Repository;

@Service
public class BidirectionService02 {

	@Autowired
	private Teacher04Repository teacherRepo;

	@Autowired
	private Course04Repository courseRepo;

	// Approach -03: Tried by persisting reference entity first and attaching it to
	// owning/parent entity

//		Hibernate: insert into teacher_03 (name) values (?)
//		Hibernate: insert into course_03 (name, teacher_id) values (?, ?)
//		Hibernate: insert into course_03 (name, teacher_id) values (?, ?)
	public void createTeacher03() {

		Teacher04 teacher = new Teacher04();
		teacher.setName("Teacher-01");
		Teacher04 updatedTeacher = teacherRepo.save(teacher);

		Random rand = new Random();

		List<Course04> courses = new ArrayList<>();
		for (int i = 0; i < 2; i++) {

			Course04 course = new Course04("Java- " + rand.nextInt());
			course.setTeacher(updatedTeacher);
			courses.add(course);
		}
		courseRepo.saveAll(courses);

	}

	public void retriveTeacherDetails() {

		teacherRepo.findAll().stream().forEach(teacher -> {

			System.out.println(teacher);
			System.out.println(teacher.getName());
			System.out.println(teacher.getCourses() != null && teacher.getCourses().get(0) != null
					? teacher.getCourses().get(0).getName()
					: "");
		});

	}

	public void retriveCourseDetails() {

		courseRepo.findAll().stream().forEach(course -> {
			System.out.println(course);
			System.out.println(course.getTeacher() != null ? course.getTeacher().getName() : "");
		});

	}

}
