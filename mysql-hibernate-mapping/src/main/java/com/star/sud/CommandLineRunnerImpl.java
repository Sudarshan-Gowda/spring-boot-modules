package com.star.sud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.star.sud.onetomany.unidirection_01.service.UniDirectionService;
import com.star.sud.onetomany.unidirection_02.service.UniDirectionService02;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

//	@Autowired
//	private UniDirectionService uniDirection01;

	@Autowired
	private UniDirectionService02 uniDirection02;

	@Override
	public void run(String... args) throws Exception {

// **** UniDirectionService approaches - 01: *****//
		// Teacher - Owning entity & Course - Reference entity
//		unidirection01.createCourse(); // this worked
//		unidirection01.createTeacher(); // this worked
//		unidirection01.createTeacher02(); // this doesn't work
//		unidirection01.createTeacher03(); // //this worked

// **** UniDirectionService approaches - 02: *****//
		// Course - Owning entity & Teacher - Reference entity
		uniDirection02.createCourse(); // this worked
		uniDirection02.createTeacher(); // this worked
		// uniDirection02.createTeacher02(); // this doesn't work
		uniDirection02.createTeacher03(); // //this worked

	}

}