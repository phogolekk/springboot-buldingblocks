package com.stacksimplify.app.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import com.stacksimplify.app.exceptions.UserNameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.stacksimplify.app.entities.User;
import com.stacksimplify.app.exceptions.UserExistsException;
import com.stacksimplify.app.exceptions.UserNotFoundException;
import com.stacksimplify.app.repositories.UserRepositiory;
import com.stacksimplify.app.services.UserService;

//annotated the controller

@RestController
@RequestMapping(value = "/users")
@Validated
public class UserController {

	// Autowire the userservice

	@Autowired
	private UserService userService;

	@GetMapping()
	public List<User> getAllUsers() {

		return userService.getAllUsers();

	}

	// create user method
	// annotate it with @RequestBody
	// annotate it with @postmapping

	@PostMapping()
	public ResponseEntity<Void> createUser(@Valid @RequestBody User user, UriComponentsBuilder builder) {

		try {
			userService.createUser(user);
			HttpHeaders headers= new HttpHeaders();
			
			headers.setLocation(builder.path("/users/{id}").buildAndExpand(user.getId()).toUri());
			return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
		} catch (UserExistsException ex) {
			
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}

	}

	// create getuserby id

	@GetMapping("/{id}")
	public Optional<User> getUserById(@Min (1) @PathVariable("id") Long id) {

		try {
			return userService.getUserById(id);
		} catch (UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, ex.getMessage());
		}

	}

	// create controller method for updating user by id
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable Long id, @RequestBody User user) {

		try {
			return userService.updateUserById(id, user);

		} catch (UserNotFoundException ex) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}

	}

	// create delete controller method for deleteuser by id

	@DeleteMapping("/{id}")
	public void deleteUserById(@PathVariable Long id) {

		userService.deleteUserById(id);
	}

	// get user by username

	@GetMapping("/byUsername/{username}")
	public User getUserbyUsername(@PathVariable String username) throws UserNameNotFoundException {

		User user= userService.getUserByUsername(username);
		if(user==null)
			throw new  UserNameNotFoundException("Username: " +username +" not found in user repository");
		return user;

	}

}
