package com.star.sud.onetomany.unidirection_02.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.onetomany.unidirection_02.entity.Course02;
import com.star.sud.onetomany.unidirection_02.entity.Teacher02;
import com.star.sud.onetomany.unidirection_02.repository.Course02Repository;
import com.star.sud.onetomany.unidirection_02.repository.Teacher02Repository;

@Service
public class UniDirectionService02 {

	@Autowired
	private Course02Repository courseRepo;

	@Autowired
	private Teacher02Repository teacherRepo;

	// Approach -01: Store the entities Individually
	public void createTeacher() {
		Teacher02 teacher = new Teacher02();
		teacher.setName("Teacher-" + new Random().nextInt());

		teacherRepo.save(teacher);
	}

	public void createCourse() {
		Course02 course = new Course02();
		course.setName("Java-" + new Random().nextInt());
		courseRepo.save(course);
	}

	// Approach -02: When we tried with attaching Teacher to Course without
	// persisting will give error:
	/**
	 * org.springframework.dao.InvalidDataAccessApiUsageException:
	 * org.hibernate.TransientPropertyValueException: object references an unsaved
	 * transient instance - save the transient instance before flushing
	 * 
	 */
	public void createTeacher02() {
		Teacher02 teacher = new Teacher02();
		Random random = new Random();
		teacher.setName("Teacher-" + random.nextInt());

		Course02 course = new Course02("Course-" + random.nextInt());
		course.setTeacher(teacher);
		courseRepo.save(course);
	}

	// Approach -03: Tried by persisting reference entity first and attaching it to
	// owning/parent entity

	public void createTeacher03() {
		Teacher02 teacher = new Teacher02();
		Random random = new Random();
		teacher.setName("Teacher-" + random.nextInt());
		teacherRepo.save(teacher);

		Course02 course = new Course02("Course-" + random.nextInt());
		course.setTeacher(teacher);
		courseRepo.save(course);
	}

}
