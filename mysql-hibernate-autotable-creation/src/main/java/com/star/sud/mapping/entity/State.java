package com.star.sud.mapping.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "STATE")
public class State extends AbstractEntity {

	private static final long serialVersionUID = -5107567802111056119L;

	@Id
	@Column(name = "CODE", unique = true, nullable = false, updatable = false, length = 3)
	private String code;

	@Column(name = "NAME", nullable = false)
	private String name;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "STATUS", nullable = false)
	private String status;

	// Constructors
	////////////////
	public State() {
		super();
	}

	public State(String code) {
		super();
		this.code = code;
	}

	public State(String code, String name, String status) {
		super();
		this.code = code;
		this.name = name;
		this.status = status;
	}

	// Getter & Setters
	////////////////////
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
