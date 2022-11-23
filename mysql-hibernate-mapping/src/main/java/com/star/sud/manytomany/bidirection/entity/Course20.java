package com.star.sud.manytomany.bidirection.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

//Owning entity
@Entity
@Table(name = "COURSE_20")
public class Course20 {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(name = "STUDENTS_COURSES_20", joinColumns = {
			@JoinColumn(name = "COURSE_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
					@JoinColumn(name = "STUDENT_ID", referencedColumnName = "ID") })
	private List<Student20> students;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Student20> getStudents() {
		return students;
	}

	public void setStudents(List<Student20> students) {
		this.students = students;
	}

}
