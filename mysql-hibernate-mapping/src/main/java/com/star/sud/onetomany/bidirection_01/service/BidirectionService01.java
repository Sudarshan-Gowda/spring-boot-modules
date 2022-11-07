package com.star.sud.onetomany.bidirection_01.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.onetomany.bidirection_01.entity.Course03;
import com.star.sud.onetomany.bidirection_01.entity.Teacher03;
import com.star.sud.onetomany.bidirection_01.repository.Course03Repository;
import com.star.sud.onetomany.bidirection_01.repository.Teacher03Repository;

@Service
public class BidirectionService01 {

	@Autowired
	private Teacher03Repository teacherRepo;

	@Autowired
	private Course03Repository courseRepo;

	// Approach -01: Store the entities Individually
	public void createTeacher() {
		Teacher03 teacher = new Teacher03();
		teacher.setName("Teacher-01");

		teacherRepo.save(teacher);
	}

	public void createCourse() {
		Course03 course = new Course03();
		course.setName("Java");
		courseRepo.save(course);
	}

	// Approach -02: When we tried with attaching Course to Teacher without
	// persisting - this won't throw exception, instead this will just save the
	// Teacher entity data and course entity won't be persisted

	public void createTeacher02() {
		Teacher03 teacher = new Teacher03();
		teacher.setName("Teacher-01");

		Random rand = new Random();

		List<Course03> courses = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			courses.add(new Course03("Java- " + rand.nextInt()));

		}

		teacher.setCourses(courses);

		teacherRepo.save(teacher);// this will just save the Student entity
	}

	// Approach -03: Tried by persisting reference entity first and attaching it to
	// owning/parent entity

//	Hibernate: insert into teacher_03 (name) values (?)
//	Hibernate: insert into course_03 (name, teacher_id) values (?, ?)
//	Hibernate: insert into course_03 (name, teacher_id) values (?, ?)
	public void createTeacher03() {

		Teacher03 teacher = new Teacher03();
		teacher.setName("Teacher-01");
		Teacher03 updatedTeacher = teacherRepo.save(teacher);

		Random rand = new Random();

		List<Course03> courses = new ArrayList<>();
		for (int i = 0; i < 2; i++) {

			Course03 course = new Course03("Java- " + rand.nextInt());
			course.setTeacher(updatedTeacher);
			courses.add(course);
		}
		courseRepo.saveAll(courses);

	}

}
