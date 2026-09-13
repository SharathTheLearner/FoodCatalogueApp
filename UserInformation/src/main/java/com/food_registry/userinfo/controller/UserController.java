package com.food_registry.userinfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food_registry.userinfo.dto.UserDTO;
import com.food_registry.userinfo.service.UserService;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
//	TODO: Add user API endpoint
	@PostMapping("/addUser")
	public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO){
		UserDTO addedUser = userService.addUser(userDTO);
		return new ResponseEntity<>(addedUser, HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchById/{id}")
	public ResponseEntity<UserDTO> findUserByID(@PathVariable("id") Integer id){
		return userService.fetchUserByID(id);
	}
	
}
