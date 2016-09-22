package com.movieflix.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.movieflix.app.entity.User;
import com.movieflix.app.exceptions.EntityAlreadyExistsException;
import com.movieflix.app.exceptions.EntityNotFoundException;
import com.movieflix.app.repository.UserRepository;

@Service

public class UserServiceImplem implements UserService {

	@Autowired
	private UserRepository repository;

	@Override
	@Transactional
	public User create(User u) {
		User existing = repository.findByEmail(u.getEmail());
		if (existing != null) {
			throw new EntityAlreadyExistsException("User already exists");
		}

		return repository.create(u);

	}

	public List<User> findAll() {
		return repository.findAll();
	}

	@Override
	public User findOne(String userId) {
		User user = repository.findOne(userId);
		if (user == null)
			throw new EntityNotFoundException("User not found");

		return user;
	}

	@Override
	@Transactional
	public User update(String userId, User user) {
		User existing = repository.findOne(userId);
		if (existing == null)
			throw new EntityNotFoundException("User not found");

		return repository.update(user);
	}

	@Override
	@Transactional
	public void delete(String userId) {
		User user = repository.findOne(userId);
		if (user == null)
			throw new EntityNotFoundException("User not found");
		repository.delete(user);

	}

	@Override
	@Transactional
	public void activateUser(String userId) {
		User user = repository.findOne(userId);
		if (user == null)
			throw new EntityNotFoundException("User not found");
		user.setActive(true);
		repository.update(user);

	}

	@Override
	public User authenticateUser(String email, String password) {
		User user = repository.findByLoginCredentials(email, password);
		if (user == null)
			throw new EntityNotFoundException("User not found");

		if (!user.isActive()) {
			throw new EntityNotFoundException("User not found or not active ");
		}

		return user;
	}

}
