package com.star.sud.onetoone.bidirection_02.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "COURSE_10")
public class Course10 implements Serializable {

	private static final long serialVersionUID = 2868276151207151641L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Integer id;

	@Column(name = "NAME")
	private String name;

	@OneToOne(cascade = CascadeType.PERSIST)
	@JoinColumn(name = "COURSE_ID", referencedColumnName = "ID")
	private CourseMaterial10 material;

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

	public CourseMaterial10 getMaterial() {
		return material;
	}

	public void setMaterial(CourseMaterial10 material) {
		this.material = material;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
