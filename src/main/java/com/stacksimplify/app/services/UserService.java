package com.stacksimplify.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stacksimplify.app.entities.User;
import com.stacksimplify.app.repositories.UserRepositiory;

//Annotated it with service
@Service
public class UserService {
	
	//autowire userrepostory
	
	@Autowired
	private UserRepositiory userRepository;
	
	
	//create getAllusers method
	
	public List<User> getAllUsers() {
		
		
		return userRepository.findAll();
		
	}
	
	
	//create createusermethod
	public User createUser(User user) {
		
		return userRepository.save(user);
		
	}
	
	
	//create get userby id
	
	
	public Optional<User> getUserById(Long id) {
		
		
		Optional<User> user = userRepository.findById(id);
		
		return user;
		
		
	}
	
	//update user by id
	
	public User updateUserById(Long id, User user) {
		
		user.setId(id);
		
		return userRepository.save(user);
		
		
	}
	
	//delete user by ID
	
	public void deleteUserById(Long id) {
		
		if(userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);
			
		}
	}
	
	//Get user by username
	
	public User getUserByUsername(String username) {
		
		return userRepository.findByUsername(username);
	
	}

}
