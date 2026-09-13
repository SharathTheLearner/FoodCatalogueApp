package com.food_registry.userinfo.mapper;

import org.springframework.stereotype.Component;

import com.food_registry.userinfo.dto.UserDTO;
import com.food_registry.userinfo.entity.User;

@Component
public class UserMapper {
	
//	Entity to DTO
	public UserDTO toDTO(User user) {
		if(user == null) return null;
		
		UserDTO dto = new UserDTO();
		
		dto.setUserid(user.getUserid());
		dto.setUserName(user.getUserName());
		dto.setUserPassword(user.getUserPassword());
		dto.setAddress(user.getAddress());
		dto.setCity(user.getCity());
		
		return dto;
	}
	
	
//	DTO to Entity
	public User toEntity(UserDTO dto) {
		if(dto == null) return null;
		
		User user = new User();
		
		user.setUserid(dto.getUserid());
		user.setUserName(dto.getUserName());
		user.setUserPassword(dto.getUserPassword());
		user.setAddress(dto.getAddress());
		user.setCity(dto.getCity());
		
		
		return user;
	}
}
