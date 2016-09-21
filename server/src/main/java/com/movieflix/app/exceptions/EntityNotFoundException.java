package com.movieflix.app.exceptions;

public class EntityNotFoundException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3783831351121379712L;

	public EntityNotFoundException(String message) {
		super(message);
	}

}
