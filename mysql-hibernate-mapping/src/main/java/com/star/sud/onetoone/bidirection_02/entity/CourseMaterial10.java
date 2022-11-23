package com.star.sud.onetoone.bidirection_02.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE_MATERIAL_10")
public class CourseMaterial10 implements Serializable {

	private static final long serialVersionUID = 7576468965727593079L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@OneToOne(mappedBy = "material")
	private Course10 course;

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

	public Course10 getCourse() {
		return course;
	}

	public void setCourse(Course10 course) {
		this.course = course;
	}

}
