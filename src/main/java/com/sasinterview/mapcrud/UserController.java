package com.sasinterview.mapcrud;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sasinterview.mapcrud.exceptions.DuplicateUserException;
import com.sasinterview.mapcrud.exceptions.InvalidInputException;
import com.sasinterview.mapcrud.exceptions.MissingUserException;
import com.sasinterview.mapcrud.model.User;
import com.sasinterview.mapcrud.model.Users;
import com.sasinterview.mapcrud.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	UserService userService;
	
	/**
	 * Get a list of all users
	 * 
	 * @return ResponseEntity<Users>
	 */
	@GetMapping
	public ResponseEntity<Users> getUsers () {
		Users users = userService.getUsers();
		return new ResponseEntity<Users>(users, HttpStatus.OK);
	}
	
	/**
	 * Get a user by id
	 * 
	 * @param id
	 * @return ResponseEntity<User>
	 */
	@GetMapping("/{id}")
	public ResponseEntity<User> getUser (@PathVariable String id) {
		User user;
		try {
			user = userService.getUser(id);
		} catch (InvalidInputException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} catch (MissingUserException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	/**
	 * Create a new user
	 * 
	 * @param user
	 * @return ResponseEntity
	 */
	@PostMapping
	public ResponseEntity createUser (@RequestBody User user) {
		try {
			userService.createUser(user);			
		} catch (InvalidInputException | DuplicateUserException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	/**
	 * Update a user
	 * 
	 * @param user
	 * @return ResponseEntity
	 */
	@PutMapping("/{id}")
	public ResponseEntity updateUser (@PathVariable String id, @RequestBody User user) {
		try {
			userService.updateUser(id, user);
		} catch (InvalidInputException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} catch (MissingUserException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	/**
	 * Delete a user
	 * 
	 * @param id
	 * @return ResponseEntity
	 */
	@DeleteMapping("/{id}")
	public ResponseEntity deleteUser (@PathVariable String id) {
		try {
			userService.deleteUser(id);
		} catch (InvalidInputException e) {
			return new ResponseEntity(HttpStatus.BAD_REQUEST);
		} catch (MissingUserException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity(HttpStatus.NO_CONTENT);
	}
}



