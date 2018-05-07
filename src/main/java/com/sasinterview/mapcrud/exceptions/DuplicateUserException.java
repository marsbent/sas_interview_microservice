package com.sasinterview.mapcrud.exceptions;

import com.sasinterview.mapcrud.model.User;

public class DuplicateUserException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DuplicateUserException (User user) {
		super("A user already exists with the id " + user.getUserId());
	}
}
