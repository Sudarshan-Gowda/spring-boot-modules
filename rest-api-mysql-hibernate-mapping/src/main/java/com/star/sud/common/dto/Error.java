package com.star.sud.common.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class Error implements Serializable {

	// Static Attributes
	//////////////////////
	private static final long serialVersionUID = -8973217867402758079L;

	// Attributes
	/////////////////
	private List<ErrorDetail> errors;

	// Constructors
	////////////////////
	public Error() {
		super();
	}

	/**
	 * @param errors
	 */
	public Error(List<ErrorDetail> errors) {
		super();
		this.errors = errors;
	}

	// Properties
	/////////////////
	public List<ErrorDetail> getErrors() {
		return errors;
	}

	public void setErrors(List<ErrorDetail> errors) {
		this.errors = errors;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}