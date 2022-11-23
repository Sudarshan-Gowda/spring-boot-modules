package com.star.sud.manytomany.bidirection.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.manytomany.bidirection.entity.Course20;
import com.star.sud.manytomany.bidirection.entity.Student20;
import com.star.sud.manytomany.bidirection.repository.Course20Repository;

@Service
public class ManyToManyService {

	@Autowired
	private Course20Repository courseRepo;

	public void createCourseStudents() {

		Random random = new Random(1);

		Student20 student1 = new Student20();
		student1.setName("Student- " + random.nextInt());

		Student20 student2 = new Student20();
		student2.setName("Student- " + random.nextInt());

		Course20 course1 = new Course20();
		course1.setName("Course-" + random.nextInt());

		List<Student20> students = new ArrayList<>();
		students.add(student1);
		students.add(student2);

		course1.setStudents(students);

		courseRepo.save(course1);

	}

}
