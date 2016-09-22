package com.movieflix.app.repository;

import java.util.List;

import com.movieflix.app.entity.User;

public interface UserRepository {
	List<User> findAll();

	User findOne(String userId);

	User create(User u);

	User update(User user);

	User findByEmail(String email);

	void delete(User existing);

	User findByLoginCredentials(String email, String password);
}
