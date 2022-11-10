package com.star.sud.onetomany.bidirection_optionality.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

// Owning entity
@Entity
@Table(name = "COURSE_05")
public class Course05 implements Serializable {

	private static final long serialVersionUID = -6809675857338245597L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@ManyToOne(optional = false)
	@JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
	private Teacher05 teacher;

	public Course05(String name) {
		super();
		this.name = name;
	}

	public Course05(String name, Teacher05 teacher) {
		super();
		this.name = name;
		this.teacher = teacher;
	}

	public Course05() {
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

	public Teacher05 getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher05 teacher) {
		this.teacher = teacher;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
