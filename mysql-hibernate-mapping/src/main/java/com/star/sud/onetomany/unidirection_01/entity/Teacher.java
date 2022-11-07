package com.star.sud.onetomany.unidirection_01.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TEACHER")
public class Teacher implements Serializable {

	// Static Properties
	//////////////////
	private static final long serialVersionUID = -1359008049181111234L;

	// Private Properties
	/////////////////
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private int id;

	@Column(name = "NAME", nullable = false)
	private String name;

	@OneToMany
	@JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
	private List<Course> courses;

	// Constructors
	/////////////////
	public Teacher() {
		super();
	}

	public Teacher(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	// Properties
	///////////////
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Course> getCourses() {
		return courses;
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}

}
