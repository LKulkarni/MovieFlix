package com.movieflix.app.service;

import java.util.List;

import com.movieflix.app.entity.SubscriptionPlan;

public interface SubscriptionPlanService {

	List<SubscriptionPlan> findAll();

	SubscriptionPlan addPlan(SubscriptionPlan plan);

	SubscriptionPlan findPlan(String planId);

	SubscriptionPlan updatePlan(String planId, SubscriptionPlan plan);

	void deletePlan(String planId);

}
