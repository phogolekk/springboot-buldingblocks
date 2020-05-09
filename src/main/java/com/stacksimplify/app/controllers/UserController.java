package com.stacksimplify.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.app.entities.User;
import com.stacksimplify.app.repositories.UserRepositiory;
import com.stacksimplify.app.services.UserService;

//annotated the controller

@RestController
public class UserController {
	
	//Autowire the userservice
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/users")
	public List<User> getAllUsers(){
		
		
		return userService.getAllUsers();
		
	}
	
	
	
	//create user method
	//annotate it with @RequestBody
	//annotate it with @postmapping
	
	@PostMapping("/users")
	public User createUser(@RequestBody User user) {
		
		return userService.createUser(user);
		
	}
	
	//create getuserby id
	
	@GetMapping("/users/{id}")
	public Optional<User> getUserById(@PathVariable("id") Long id ){ 
	
		return userService.getUserById(id);
		
		
	}
	//create controller method for updating user by id
	@PutMapping("/users/{id}")
	public User updateUserById(@PathVariable Long id, @RequestBody User user) {
		
		return userService.updateUserById(id, user);
	
	}
	
	//create delete controller method for deleteuser by id
	
	@DeleteMapping("users/{id}")
	public void deleteUserById(@PathVariable Long id) {
		
		userService.deleteUserById(id);
	}

	//get user by username
	
	@GetMapping("/users/byUsername/{username}")
	public User getUserbyUsername(@PathVariable String username) {
		
		
		return userService.getUserByUsername(username);
		
		
	}
	
}
