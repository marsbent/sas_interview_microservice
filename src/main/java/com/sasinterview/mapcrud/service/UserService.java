package com.sasinterview.mapcrud.service;

import java.util.HashMap;

import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.stereotype.Service;

import com.sasinterview.mapcrud.UserController;
import com.sasinterview.mapcrud.Utils;
import com.sasinterview.mapcrud.exceptions.DuplicateUserException;
import com.sasinterview.mapcrud.exceptions.InvalidInputException;
import com.sasinterview.mapcrud.exceptions.MissingUserException;
import com.sasinterview.mapcrud.model.User;
import com.sasinterview.mapcrud.model.Users;

@Service
public class UserService {
	// Normally I would have this map id to a User object
	// however the instructions said to use a map of string to string
	private HashMap<String, String> userData;
	
	// This could also be an enum
	private static final String ALL_USERS_REL = "all_users";
	
	public UserService () {
		this.userData = new HashMap<>();
	}

	private void validateUser (User user) {
		if (user == null) throw new InvalidInputException("User is null");
		if (Utils.isNullOrEmpty(user.getUserId())) throw new InvalidInputException("User id is null or empty");
		if (Utils.isNullOrEmpty(user.getName())) throw new InvalidInputException("User name is null or empty");
	}
	
	public User getUser (String id) {
		if (Utils.isNullOrEmpty(id)) throw new InvalidInputException("Missing id");
		if (!userData.containsKey(id)) throw new MissingUserException(id);
		
		User user = new User(id, userData.get(id));
		user.add(ControllerLinkBuilder.linkTo(UserController.class).slash(user.getUserId()).withSelfRel());
		user.add(ControllerLinkBuilder.linkTo(UserController.class).withRel(ALL_USERS_REL));
		return user;
	}
	
	public Users getUsers () {
		Users users = new Users();

		// Only add a /users self link if the users is empty
		// If users are returned, each user will have a /users link
		// in addition to a /user/id link
		if (userData.isEmpty()) {
			users.add(ControllerLinkBuilder.linkTo(UserController.class).withSelfRel());
			return users;
		}
		
		for (String userId : userData.keySet()) {
			User user = getUser(userId);
			users.addUser(user);
		}
		return users;
	}
	
	public void createUser (User user) {
		validateUser(user);
		if (userData.containsKey(user.getUserId())) throw new DuplicateUserException(user);
		
		userData.put(user.getUserId(), user.getName());
	}
	
	public void updateUser (String id, User user) {
		validateUser(user);
		if (!userData.containsKey(id)) throw new MissingUserException(id);
		
		// If id has been updated, delete and replace
		if (user.getUserId() != id) userData.remove(id);

		userData.put(user.getUserId(), user.getName());
	}
	
	public void deleteUser (String id) {
		if (Utils.isNullOrEmpty(id)) throw new InvalidInputException("Missing id");
		if (!userData.containsKey(id)) throw new MissingUserException(id);
		
		userData.remove(id);
	}
}
