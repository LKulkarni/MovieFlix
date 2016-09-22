package com.movieflix.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movieflix.app.entity.SubscriptionPlan;
import com.movieflix.app.service.SubscriptionPlanService;

@RestController
@RequestMapping(value = "/userplans", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SubscriptionPlanController {

	@Autowired
	SubscriptionPlanService service;

	@RequestMapping(method = RequestMethod.GET)
	public List<SubscriptionPlan> findAll() {
		return service.findAll();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public SubscriptionPlan findPlan(@RequestParam("id") String planId) {
		return service.findPlan(planId);
	}

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SubscriptionPlan addPlan(@RequestBody SubscriptionPlan plan) {

		return service.addPlan(plan);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public SubscriptionPlan updatePlan(@RequestParam("id") String planId, @RequestBody SubscriptionPlan plan) {

		return service.updatePlan(planId, plan);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public void deletePlan(@RequestParam("id") String planId) {
		 service.deletePlan(planId);
	}

}
