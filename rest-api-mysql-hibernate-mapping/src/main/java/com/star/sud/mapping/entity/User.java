package com.star.sud.mapping.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.star.sud.mapping.common.CustomGenerator;

@Entity
@Table(name = "USER")
public class User extends AbstractEntity {

	private static final long serialVersionUID = 2226171587712701332L;

	// Private Properties
	/////////////////////
	@Id
	@GenericGenerator(name = "USER_ID_SEQ", strategy = "com.star.sud.mapping.common.CustomGenerator", parameters = {
			@Parameter(name = CustomGenerator.INCREMENT_PARAM, value = "50"),
			@Parameter(name = CustomGenerator.VALUE_PREFIX_PARAMETER, value = "USER"),
			@Parameter(name = CustomGenerator.NUMBER_FORMAT_PARAMETER, value = "%05d") })
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ")
	@Column(name = "USER_ID", nullable = false, unique = true, length = 50)
	private String userId;

	@Column(name = "USER_NAME", nullable = false, unique = true, length = 20)
	private String userName;

	@Column(name = "PASSWORD", nullable = false)
	private String password;

	@Column(name = "EMAIL", nullable = false, unique = true, length = 150)
	private String email;

	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;

	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;

	@OneToOne(optional = false)
	@JoinColumn(name = "GENDER")
	private Gender gender;

	@Column(name = "DOB")
	@Temporal(TemporalType.DATE)
	private Date dob;

	@OneToMany(mappedBy = "user")
	private List<AddressDetails> addressDetails;

	// Mapped Entities
	///////////////////
	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "USER_ROLE", joinColumns = {
			@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID") }, inverseJoinColumns = {
					@JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID") })
	private List<Role> roles;

	// Getter & Setters
	///////////////////
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public List<AddressDetails> getAddressDetails() {
		return addressDetails;
	}

	public void setAddressDetails(List<AddressDetails> addressDetails) {
		this.addressDetails = addressDetails;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

}
