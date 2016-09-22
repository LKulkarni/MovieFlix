package com.movieflix.app.entity;

import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Table
@Entity(name = "User")
public class User {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	@Column(unique = true)
	private String email;
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;
	private String password;
	private boolean active;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private UserPayment userPayment;
	@ManyToOne(fetch = FetchType.LAZY)
	private SubscriptionPlan userPlan;

	public User() {

		this.id = UUID.randomUUID().toString();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public UserPayment getUserPayment() {
		return userPayment;
	}

	public void setUserPayment(UserPayment userPayment) {
		this.userPayment = userPayment;
	}

	public SubscriptionPlan getUserPlan() {
		return userPlan;
	}

	public void setUserPlan(SubscriptionPlan userPlan) {
		this.userPlan = userPlan;
	}

}
