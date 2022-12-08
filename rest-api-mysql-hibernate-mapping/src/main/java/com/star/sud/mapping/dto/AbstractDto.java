package com.star.sud.mapping.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public abstract class AbstractDto implements Serializable {

	// Static Properties
	//////////////////
	private static final long serialVersionUID = 3842352594707291443L;

	// Private Properties
	////////////////
	private String createdBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;

	private String updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
