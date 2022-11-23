package com.star.sud.onetoone.bidirection_01.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE_09")
public class Course09 implements Serializable {

	private static final long serialVersionUID = 2868276151207151641L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@OneToOne(mappedBy = "course")
	private CourseMaterial09 material;

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

	public CourseMaterial09 getMaterial() {
		return material;
	}

	public void setMaterial(CourseMaterial09 material) {
		this.material = material;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
