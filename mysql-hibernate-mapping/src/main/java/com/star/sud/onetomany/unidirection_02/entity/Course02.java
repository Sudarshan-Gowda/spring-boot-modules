package com.star.sud.onetomany.unidirection_02.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE_02")
public class Course02 implements Serializable {

	// Static Properties
	/////////////////
	private static final long serialVersionUID = -3021683677257739611L;

	// Private properties
	////////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Integer id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@ManyToOne
	@JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
	private Teacher02 teacher;

	// Constructors
	////////////////
	public Course02() {
		super();
	}

	public Course02(String name) {
		super();
		this.name = name;
	}

	public Course02(Integer id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	// Properties
	///////////////
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

	public Teacher02 getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher02 teacher) {
		this.teacher = teacher;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
