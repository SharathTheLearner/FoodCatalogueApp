package com.food_registry.userinfo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.food_registry.userinfo.dto.UserDTO;
import com.food_registry.userinfo.entity.User;
import com.food_registry.userinfo.mapper.UserMapper;
import com.food_registry.userinfo.repo.UserRepo;

import org.springframework.http.HttpStatus;

@Service
public class UserService {
	@Autowired
	UserRepo userRepo;
	
	@Autowired
	UserMapper userMapper;
	
	public UserDTO addUser(UserDTO userDto) {
		User user = userMapper.toEntity(userDto);
		User saved = userRepo.save(user);
		return userMapper.toDTO(saved);
	}

	public ResponseEntity<UserDTO> fetchUserByID(Integer id) {
		// TODO Auto-generated method stub
		Optional<User> user = userRepo.findById(id);
		if(user.isPresent()) {
			UserDTO userDTO = userMapper.toDTO(user.get());
			return new ResponseEntity<>(userDTO, HttpStatus.OK);
		}
		return ResponseEntity.notFound().build();
		
	}
}
