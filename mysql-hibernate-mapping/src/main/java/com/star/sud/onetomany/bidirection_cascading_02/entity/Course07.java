package com.star.sud.onetomany.bidirection_cascading_02.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE_07")
public class Course07 implements Serializable {

	private static final long serialVersionUID = -6809675857338245597L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@ManyToOne
	private Teacher07 teacher;

	public Course07(String name) {
		super();
		this.name = name;
	}

	public Course07(String name, Teacher07 teacher) {
		super();
		this.name = name;
		this.teacher = teacher;
	}

	public Course07() {
		super();
	}

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

	public Teacher07 getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher07 teacher) {
		this.teacher = teacher;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
