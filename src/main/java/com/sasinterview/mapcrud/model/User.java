package com.sasinterview.mapcrud.model;

import org.springframework.hateoas.ResourceSupport;

public class User extends ResourceSupport {
	
	private String userId;
	private String name;
	
	public User () {};

	public User (String id, String name) {
		this.userId = id;
		this.name = name;
	}

	public String getUserId () {
		return userId;
	}

	public void setUserId (String userId) {
		this.userId = userId;
	}

	public String getName () {
		return name;
	}

	public void setName (String name) {
		this.name = name;
	}
}
