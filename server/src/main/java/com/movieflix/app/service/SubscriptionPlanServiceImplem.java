package com.movieflix.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.movieflix.app.entity.SubscriptionPlan;
import com.movieflix.app.exceptions.EntityAlreadyExistsException;
import com.movieflix.app.exceptions.EntityNotFoundException;
import com.movieflix.app.repository.SubscriptionPlanRepository;

@Service
public class SubscriptionPlanServiceImplem implements SubscriptionPlanService {

	@Autowired
	private SubscriptionPlanRepository repository;

	@Override
	public List<SubscriptionPlan> findAll() {
		return repository.findAll();
	}

	@Override
	@Transactional
	public SubscriptionPlan addPlan(SubscriptionPlan plan) {
		SubscriptionPlan existing = repository.findByName(plan.getPlanName());
		if (existing != null) {
			throw new EntityAlreadyExistsException("The plan already exists");
		}
		return repository.addUserPlan(plan);
	}

	@Override
	public SubscriptionPlan findPlan(String planId) {

		return repository.findPlan(planId);
	}

	@Override
	@Transactional
	public SubscriptionPlan updatePlan(String planId, SubscriptionPlan plan) {
		SubscriptionPlan existing = repository.findPlan(planId);
		if (existing == null) {
			throw new EntityNotFoundException("No Plan Found ");
		}
		return repository.updatePlan(plan);
	}

	@Override
	@Transactional
	public void deletePlan(String planId) {
		SubscriptionPlan existing = repository.findPlan(planId);
		if (existing == null) {
			throw new EntityNotFoundException("No Plan Found ");
		}
		repository.deletePlan(existing);
	}

}
