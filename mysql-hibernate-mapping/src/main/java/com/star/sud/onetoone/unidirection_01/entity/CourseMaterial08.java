package com.star.sud.onetoone.unidirection_01.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE_MATERIAL_08")
public class CourseMaterial08 implements Serializable {

	private static final long serialVersionUID = 7576468965727593079L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@OneToOne(optional = false)
	@JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
	private Course08 course08;

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

	public Course08 getCourse08() {
		return course08;
	}

	public void setCourse08(Course08 course08) {
		this.course08 = course08;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
