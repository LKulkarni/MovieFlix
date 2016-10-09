package com.movieflix.app.repository;

import java.util.List;

import com.movieflix.app.entity.SubscriptionPlan;

public interface SubscriptionPlanRepository {

	List<SubscriptionPlan> findAll();

	SubscriptionPlan findByName(String planName);

	SubscriptionPlan addUserPlan(SubscriptionPlan plan);

	SubscriptionPlan findPlan(String planId);

	SubscriptionPlan updatePlan(SubscriptionPlan plan);

	void deletePlan(SubscriptionPlan plan);



}
