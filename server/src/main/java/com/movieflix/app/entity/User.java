package com.movieflix.app.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	private String role;
	@Temporal(TemporalType.TIMESTAMP)
	private Date memberSince;
	private boolean active;
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private UserPayment userPayment;
	@ManyToOne(fetch = FetchType.LAZY)
	private SubscriptionPlan userPlan;
	@ManyToMany
	@JoinTable(name = "User_Video", joinColumns = @JoinColumn(name = "User_Id"), inverseJoinColumns = @JoinColumn(name = "Videos_Id"))
	private Collection<Video> watched;
	@OneToMany
	private Collection<Video> favorite;

	public User() {

		this.id = UUID.randomUUID().toString();
		this.watched = new ArrayList<Video>();
		this.favorite = new ArrayList<Video>();
		this.memberSince = new Date();
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

	public Date getMemberSince() {
		return memberSince;
	}

	public void setMemberSince(Date memberSince) {
		this.memberSince = memberSince;
	}

	public Collection<Video> getWatched() {
		return watched;
	}

	public void setWatched(Collection<Video> watched) {
		this.watched = watched;
	}

	public Collection<Video> getFavorite() {
		return favorite;
	}

	public void setFavorite(Collection<Video> favorite) {
		this.favorite = favorite;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
