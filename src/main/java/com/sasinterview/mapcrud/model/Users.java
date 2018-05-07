package com.sasinterview.mapcrud.model;

import java.util.ArrayList;

import org.springframework.hateoas.ResourceSupport;

public class Users extends ResourceSupport {	
	
	private ArrayList<User> users;
	
	public Users () {
		this.users = new ArrayList<>();
	}

	public ArrayList<User> getUsers () {
		return users;
	}

	public void setUsers (ArrayList<User> users) {
		this.users = users;
	}
	
	public void addUser (User user) {
		users.add(user);
	}
}
