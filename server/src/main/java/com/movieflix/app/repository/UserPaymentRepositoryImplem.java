package com.movieflix.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.movieflix.app.entity.UserPayment;

/**
 * 
 * @author Loukik
 *
 *	This repository class connects to the User Payment table in the database
 */
@Repository
public class UserPaymentRepositoryImplem implements UserPaymentRepository {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public UserPayment findUserPayment(String cardnumber) {
		
		TypedQuery<UserPayment> query= em.createQuery("SELECT up from UserPayment up WHERE up.cardNumber=:cno",UserPayment.class);
		query.setParameter("cno", cardnumber);
		List<UserPayment> list= query.getResultList();
		if(list.size()==1)
		{
			return list.get(0);
		}
		return null;
	}

}
