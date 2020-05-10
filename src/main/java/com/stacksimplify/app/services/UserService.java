package com.stacksimplify.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.stacksimplify.app.entities.User;
import com.stacksimplify.app.exceptions.UserExistsException;
import com.stacksimplify.app.exceptions.UserNotFoundException;
import com.stacksimplify.app.repositories.UserRepositiory;

//Annotated it with service
@Service
public class UserService {

	// autowire userrepostory

	@Autowired
	private UserRepositiory userRepository;

	// create getAllusers method

	public List<User> getAllUsers() {

		return userRepository.findAll();

	}

	// create createusermethod
	public User createUser(User user) throws UserExistsException {
		
		//if user exist using username, if exist throws userexistexception
		
		User existinguser =userRepository.findByUsername(user.getUsername());
		
		if(existinguser!=null) {
			
			throw new UserExistsException("User already exist in the repository");
		}
		
		//if not exist
	return userRepository.save(user);

	}

	// create get userby id

	public Optional<User> getUserById(Long id) throws UserNotFoundException {

		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {

			throw new UserNotFoundException("User not found in user repository");
		}

		return user;

	}

	// update user by id

	public User updateUserById(Long id, User user) throws UserNotFoundException {

		Optional<User> optionaluser = userRepository.findById(id);

		if (!optionaluser.isPresent()) {

			throw new UserNotFoundException("User not found in user repository");
		}

		user.setId(id);

		return userRepository.save(user);

	}

	// delete user by ID

	public void deleteUserById(Long id) {

		Optional<User> deluser = userRepository.findById(id);

		if (!deluser.isPresent()) {

			throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User not found in the repo");
		}

		else if (userRepository.findById(id).isPresent()) {
			userRepository.deleteById(id);

		}

	}

	// Get user by username

	public User getUserByUsername(String username) {

		return userRepository.findByUsername(username);

	}

}
