package com.movieflix.app.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.movieflix.app.entity.User;


@Repository
public class UserRepositoryImplem implements UserRepository {

	@PersistenceContext
	private EntityManager em;

	@Override
	public User create(User u) {
		em.persist(u);
		return u;
	}

	@Override
	public List<User> findAll() {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u", User.class);
		List<User> users = query.getResultList();
		return users;

	}

	@Override
	public User findByEmail(String email) {
		TypedQuery<User> query = em.createQuery("SELECT u FROM User u WHERE u.email=:pemail ", User.class);
		query.setParameter("pemail", email);
		List<User> users = query.getResultList();
		if (users.size() == 1)
			return users.get(0);
		else
			return null;
	}

	@Override
	public User findOne(String userId) {
		// TODO Auto-generated method stub
		return em.find(User.class, userId);
	}

	@Override
	public User update(User user) {
		// TODO Auto-generated method stub
		return em.merge(user);
	}

	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		em.remove(user);
	}

}
