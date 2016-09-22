package com.movieflix.app.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SubscriptionPlan {
	@Id
	private String planId;
	@Column(unique = true)
	private String planName;
	private Integer screens;
	private boolean hd;
	private Float cost;

	public SubscriptionPlan() {
		planId = "Plan" + UUID.randomUUID().toString();
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getScreens() {
		return screens;
	}

	public void setScreens(Integer screens) {
		this.screens = screens;
	}

	public boolean isHd() {
		return hd;
	}

	public void setHd(boolean hd) {
		this.hd = hd;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public String getPlanId() {
		return planId;
	}

	public void setPlanId(String planId) {
		this.planId = planId;
	}

}
