package com.star.sud.onetomany.unidirection_01.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.onetomany.unidirection_01.entity.Course;
import com.star.sud.onetomany.unidirection_01.entity.Teacher;
import com.star.sud.onetomany.unidirection_01.repository.CourseRepository;
import com.star.sud.onetomany.unidirection_01.repository.TeacherRepository;

@Service
public class UniDirectionService {

	@Autowired
	private TeacherRepository teacherRepo;

	@Autowired
	private CourseRepository courseRepo;

	// Approach -01: Store the entities Individually
	public void createTeacher() {
		Teacher teacher = new Teacher();
		teacher.setName("Teacher-01");

		teacherRepo.save(teacher);
	}

	public void createCourse() {
		Course course = new Course();
		course.setName("Java");
		courseRepo.save(course);
	}

	// Approach -02: When we tried with attaching Course to Teacher without
	// persisting will give error:
	/**
	 * org.hibernate.TransientObjectException: object references an unsaved
	 * transient instance - save the transient instance before flushing
	 * 
	 */
	public void createTeacher02() {
		Teacher teacher = new Teacher();
		teacher.setName("Teacher-01");

		Random rand = new Random();

		List<Course> courses = new ArrayList<>();
		for (int i = 0; i < 2; i++) {
			courses.add(new Course("Java- " + rand.nextInt()));

		}

		teacher.setCourses(courses);

		teacherRepo.save(teacher);
	}

	// Approach -03: Tried by persisting reference entity first and attaching it to
	// owning/parent entity

	// Hibernate: insert into course (name) values (?)
	// Hibernate: insert into course (name) values (?)
	// Hibernate: insert into teacher (name) values (?)
	// Hibernate: update course set teacher_id=? where id=?
	// Hibernate: update course set teacher_id=? where id=?
	public void createTeacher03() {
		Teacher teacher = new Teacher();
		teacher.setName("Teacher-01");

		Random rand = new Random();

		List<Course> courses = new ArrayList<>();
		for (int i = 0; i < 2; i++) {

			Course course = new Course("Java- " + rand.nextInt());
			Course save = courseRepo.save(course);
			courses.add(save);
		}

		teacher.setCourses(courses);
		teacherRepo.save(teacher);
	}

}
