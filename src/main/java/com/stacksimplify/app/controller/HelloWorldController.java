package com.stacksimplify.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

//restcontroller
@RestController
public class HelloWorldController {
	

	//simple method
	//URI -/helloword
	//GET method
	
	//@RequestMapping(method=RequestMethod.GET, path="/helloworld")
	@GetMapping("/helloworld1")
	public String helloworld() {
	
		return "Hello world 1";
	
	}

}
