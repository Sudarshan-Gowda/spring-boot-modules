package com.star.sud.onetoone.unidirection_01.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.star.sud.onetoone.unidirection_01.entity.Course08;
import com.star.sud.onetoone.unidirection_01.entity.CourseMaterial08;
import com.star.sud.onetoone.unidirection_01.repository.Course08Repository;
import com.star.sud.onetoone.unidirection_01.repository.CourseMaterial08Repository;

@Service
public class OneToOneUnidirectionService {

	@Autowired
	private Course08Repository course08Repository;

	@Autowired
	private CourseMaterial08Repository courseMaterial08Repository;

	public void createCourseMaterial() {

		Random random = new Random(1);

		Course08 course08 = new Course08();
		course08.setName("Course- " + random.nextInt());
		course08Repository.save(course08);

		CourseMaterial08 material08 = new CourseMaterial08();
		material08.setName("Course-Materail- " + random.nextInt());
		material08.setCourse08(course08);

		courseMaterial08Repository.save(material08);

	}

}
