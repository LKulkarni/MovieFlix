package com.movieflix.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movieflix.app.entity.User;
import com.movieflix.app.entity.UserPayment;
import com.movieflix.app.exceptions.EntityAlreadyExistsException;
import com.movieflix.app.exceptions.EntityNotFoundException;
import com.movieflix.app.repository.UserPaymentRepository;
import com.movieflix.app.repository.UserRepository;

@Service

public class UserServiceImplem implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	UserPaymentRepository userPaymentRepository;

	@Override
	@Transactional
	public User create(User u) {
		User existing = userRepository.findByEmail(u.getEmail());
		if (existing != null) {
			throw new EntityAlreadyExistsException("User already exists");
		}

		UserPayment up = userPaymentRepository.findUserPayment(u.getUserPayment().getCardNumber());
		if (up != null) {
			throw new EntityAlreadyExistsException("Card already in the system");
		}
		return userRepository.create(u);

	}

	public List<User> findAll() {
		return userRepository.findAll();
	}

	@Override
	public User findOne(String userId) {
		User user = userRepository.findOne(userId);
		if (user == null)
			throw new EntityNotFoundException("User not found");

		return user;
	}

	@Override
	@Transactional
	public User update(String userId, User user) {
		User existing = userRepository.findOne(userId);
		if (existing == null)
			throw new EntityNotFoundException("User not found");

		return userRepository.update(user);
	}

	@Override
	@Transactional
	public void delete(String userId) {
		User user = userRepository.findOne(userId);
		if (user == null)
			throw new EntityNotFoundException("User not found");
		userRepository.delete(user);

	}

	@Override
	@Transactional
	public void activateUser(String userId) {
		User user = userRepository.findOne(userId);
		if (user == null)
			throw new EntityNotFoundException("User not found");
		user.setActive(true);
		userRepository.update(user);

	}

	@Override
	public User authenticateUser(String email, String password) {
		User user = userRepository.findByLoginCredentials(email, password);
		if (user == null)
			throw new EntityNotFoundException("User not found");

		if (!user.isActive()) {
			throw new EntityNotFoundException("User not found or not active ");
		}

		return user;
	}

}
