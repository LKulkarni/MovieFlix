package com.movieflix.app.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1872985612742741505L;

	public EntityAlreadyExistsException(String message) {
		super(message);
	}

}
