package com.movieflix.app.service;

import java.util.List;

import com.movieflix.app.entity.User;

public interface UserService {
	
	List<User> findAll();

	User findOne(String userId);

	User create(User user);

	User update(String userId, User user);

	void delete(String userId);

}
