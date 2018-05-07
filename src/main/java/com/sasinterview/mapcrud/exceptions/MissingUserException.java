package com.sasinterview.mapcrud.exceptions;

public class MissingUserException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public MissingUserException () {};
	
	public MissingUserException (String id) {
		super("No user with id " + id);
	}
}
