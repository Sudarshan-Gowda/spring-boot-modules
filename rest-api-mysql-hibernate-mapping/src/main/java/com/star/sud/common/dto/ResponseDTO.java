package com.star.sud.common.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class ResponseDTO<T> implements Serializable {

	// Static Attributes
	/////////////////////////
	private static final long serialVersionUID = -1151382977060328854L;

	// Attributes
	////////////////////
	private T data;

	private Error error;

	// Properties
	//////////////////
	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public Error getError() {
		return error;
	}

	public void setError(Error error) {
		this.error = error;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
