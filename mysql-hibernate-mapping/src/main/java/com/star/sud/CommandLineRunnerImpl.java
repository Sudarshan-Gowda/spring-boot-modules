
package com.star.sud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.star.sud.manytomany.bidirection.service.ManyToManyService;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

//	@Autowired
//	private UniDirectionService uniDirection01;

//	@Autowired
//	private UniDirectionService02 uniDirection02;

//	@Autowired
//	private BidirectionService01 bidirection01;

//	@Autowired
//	private BidirectionService02 bidirection02;

//	@Autowired
//	private BidirectionService03 bidirection03;

//	@Autowired
//	private BidirectionService04 bidirection04;

//	@Autowired
//	private OneToOneUnidirectionService oneToOneUnidirectionService;
//
//	@Autowired
//	private BidirectionService05 bidirection05;

//	@Autowired
//	private OneToOneBidirectionService oneToOneBidirectionService;

//	@Autowired
//	private OneToOneBidirection02Service oneToOneBidirection02Service;

	@Autowired
	private ManyToManyService manyToManyService;

	@Override
	public void run(String... args) throws Exception {

// **** UniDirectionService approaches - 01: *****//
		// Teacher - Owning entity & Course - Reference entity
//		unidirection01.createCourse(); // this worked
//		unidirection01.createTeacher(); // this worked
//		// unidirection01.createTeacher02(); // this doesn't work
//		unidirection01.createTeacher03(); // //this worked

// **** UniDirectionService approaches - 02: *****//
		// Course - Owning entity & Teacher - Reference entity
//		uniDirection02.createCourse(); // this worked
//		uniDirection02.createTeacher(); // this worked
//		// uniDirection02.createTeacher02(); // this doesn't work
//		uniDirection02.createTeacher03(); // //this worked

// **** BiDirectionService approaches - 01: *****//
		// bidirection01.createCourse();
		// bidirection01.createTeacher();
		// bidirection01.createTeacher02();
		// bidirection01.createTeacher03();

// **** BiDirectionService approaches - Eager/Lazy loading: *****//		
		// bidirection02.createTeacher03();
		// bidirection02.retriveTeacherDetails();
		// bidirection02.retriveCourseDetails();

// **** BiDirectionService approaches - Optionality: *****//		
		// bidirection03.createCourseWithoutTeacher();
		// bidirection03.createCourseWithTeacher();

// **** BiDirectionService approaches - Cascading: *****//		
		// bidirection04.createCourseWithTeacher();

// **** Bidirectional approaches - Cascading: *****//		
		// bidirection05.createTeacherWithCourse();

// **** OneToOne Unidirectional approaches: *****//		
		// oneToOneUnidirectionService.createCourseMaterial();

// **** OneToOne Bidirectional approaches: -  Owning entity as CourseMaterial (holding reference)*****//	
		// oneToOneBidirectionService.createCourseMaterial();

// **** OneToOne Bidirectional approaches: -  Owning entity as Course (not holding reference) *****//	
		// oneToOneBidirection02Service.createCourseMaterial();

		// **** ManyToMany Bidirectional approaches: - Owning entity as Course and
		// Student as reference entity *****//
		manyToManyService.createCourseStudents();

	}

}