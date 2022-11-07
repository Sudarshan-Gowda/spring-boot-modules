package com.star.sud.onetomany.unidirection_01.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE")
public class Course implements Serializable {

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

	// Constructors
	////////////////
	public Course() {
		super();
	}

	public Course(String name) {
		super();
		this.name = name;
	}

	public Course(Integer id, String name) {
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

}
