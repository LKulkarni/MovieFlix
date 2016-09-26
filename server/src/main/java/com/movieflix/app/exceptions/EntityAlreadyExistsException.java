package com.movieflix.app.exceptions;

public class EntityAlreadyExistsException extends RuntimeException {

	/**
	 * @author Loukik
	 * 
	 *         Exception for entity already exists
	 * 
	 */
	private static final long serialVersionUID = 1872985612742741505L;

	public EntityAlreadyExistsException(String message) {
		super(message);
	}

}
