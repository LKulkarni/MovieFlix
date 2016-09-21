package com.movieflix.app.repository;

import java.util.List;

import com.movieflix.app.entity.User;

public interface UserRepository {
	public List<User> findAll();

	User findOne(String userId);

	public User create(User u);

	User update(User user);

	public User findByEmail(String email);

	void delete(User existing);
}
