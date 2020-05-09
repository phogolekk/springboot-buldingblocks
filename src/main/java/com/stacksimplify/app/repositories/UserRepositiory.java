package com.stacksimplify.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stacksimplify.app.entities.User;

@Repository
public interface UserRepositiory extends JpaRepository<User, Long>{
	
	
	User findByUsername(String username);

}
