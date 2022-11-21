package com.star.sud.onetomany.bidirection_cascading_02.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "TEACHER_07")
public class Teacher07 implements Serializable {

	private static final long serialVersionUID = -8594789040975251975L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID", nullable = false, unique = true)
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@OneToMany(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "TEACHER_ID", referencedColumnName = "ID")
	private List<Course07> courses;

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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Course07> getCourses() {
		return courses;
	}

	public void setCourses(List<Course07> courses) {
		this.courses = courses;
	}

}
