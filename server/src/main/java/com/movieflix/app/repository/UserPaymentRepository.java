package com.movieflix.app.repository;

import com.movieflix.app.entity.UserPayment;

public interface UserPaymentRepository {

	UserPayment findUserPayment(String cardnumber);
	
}
