package com.star.sud.mapping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "GENDER")
public class Gender extends AbstractEntity {

	private static final long serialVersionUID = -2266568607038518557L;

	@Id
	@Column(name = "CODE", unique = true, nullable = false, updatable = false, length = 20)
	private String code;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "STATUS", nullable = false)
	private String status;

	public Gender(String code) {
		super();
		this.code = code;
	}

	public Gender() {
		super();
	}

	public Gender(String code, String name) {
		super();
		this.code = code;
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
