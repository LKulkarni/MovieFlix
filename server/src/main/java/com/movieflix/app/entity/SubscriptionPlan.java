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
	private boolean ultraHd;
	private boolean allDevices;
	private boolean cancelAnytime;
	private boolean firstMonthFree;
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

	public boolean isUltraHd() {
		return ultraHd;
	}

	public void setUltraHd(boolean ultraHd) {
		this.ultraHd = ultraHd;
	}

	public boolean isAllDevices() {
		return allDevices;
	}

	public void setAllDevices(boolean allDevices) {
		this.allDevices = allDevices;
	}

	public boolean isCancelAnytime() {
		return cancelAnytime;
	}

	public void setCancelAnytime(boolean cancelAnytime) {
		this.cancelAnytime = cancelAnytime;
	}

	public boolean isFirstMonthFree() {
		return firstMonthFree;
	}

	public void setFirstMonthFree(boolean firstMonthFree) {
		this.firstMonthFree = firstMonthFree;
	}

	@Override
	public String toString()
	{
		return "[  Plan: "+planName+" Cost:"+ cost+" ]";
	}
}
