package com.star.sud.onetomany.unidirection_02.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TEACHER_02")
public class Teacher02 implements Serializable {

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

	// Constructors
	/////////////////
	public Teacher02() {
		super();
	}

	public Teacher02(int id, String name) {
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

}
