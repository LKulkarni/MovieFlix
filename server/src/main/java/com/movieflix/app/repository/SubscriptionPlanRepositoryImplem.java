package com.movieflix.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.movieflix.app.entity.SubscriptionPlan;

/**
 * 
 * @author Loukik
 *
 *	This repository class connects to the Subscription table in the database
 */
@Repository
public class SubscriptionPlanRepositoryImplem implements SubscriptionPlanRepository {

	@PersistenceContext
	EntityManager em;

	@Override
	public List<SubscriptionPlan> findAll() {
		TypedQuery<SubscriptionPlan> query = em.createQuery("SELECT up FROM SubscriptionPlan up", SubscriptionPlan.class);
		List<SubscriptionPlan> plans = query.getResultList();
		return plans;
	}

	@Override
	public SubscriptionPlan findByName(String planName) {
		TypedQuery<SubscriptionPlan> query = em.createQuery("SELECT up FROM SubscriptionPlan up WHERE up.planName=:pname", SubscriptionPlan.class);
		query.setParameter("pname", planName);
		List<SubscriptionPlan> plans = query.getResultList();
		if (plans.size() == 1) {
			return plans.get(0);
		}
		return null;
	}

	@Override
	public SubscriptionPlan addUserPlan(SubscriptionPlan plan) {
		em.persist(plan);
		return plan;
	}

	@Override
	public SubscriptionPlan findPlan(String planId) {

		TypedQuery<SubscriptionPlan> query = em.createQuery("SELECT up FROM SubscriptionPlan up WHERE up.planId=:pid", SubscriptionPlan.class);
		query.setParameter("pid", planId);
		List<SubscriptionPlan> plans = query.getResultList();
		if (plans.size() == 1) {
			return plans.get(0);
		}
		return null;

	}

	@Override
	public SubscriptionPlan updatePlan(SubscriptionPlan plan) {
		em.merge(plan);
		return plan;
	}

	@Override
	public void deletePlan(SubscriptionPlan plan) {
		em.remove(plan);

	}

}
