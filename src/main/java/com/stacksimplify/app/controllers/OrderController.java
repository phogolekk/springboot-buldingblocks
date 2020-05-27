package com.stacksimplify.app.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stacksimplify.app.entities.Order;
import com.stacksimplify.app.entities.User;
import com.stacksimplify.app.exceptions.UserNotFoundException;
import com.stacksimplify.app.repositories.OrderRepository;
import com.stacksimplify.app.repositories.UserRepositiory;

@RestController
@RequestMapping(value = "/users")
public class OrderController {
	
	@Autowired
	private UserRepositiory userRepository;
	
	@Autowired
	private OrderRepository orderRepository;
	
	//get all orders for a user
	
	@GetMapping("/{userid}/orders")
	public List<Order> getAllOrders(@PathVariable Long userid) throws UserNotFoundException{
		
		Optional<User> userOptional = userRepository.findById(userid);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
			
		}
		return userOptional.get().getOrders();
		
		
	}
	
	//create order method
	
	@PostMapping("/{userid}/orders")
	public Order createOrder(@PathVariable Long userid, @RequestBody Order order ) throws UserNotFoundException {
		
		Optional<User> userOptional = userRepository.findById(userid);
		
		if(!userOptional.isPresent()) {
			throw new UserNotFoundException("User Not Found");
			
		}
		
		User user = userOptional.get();
		order.setUser(user);
		return orderRepository.save(order);
		
		
	}
	

}
