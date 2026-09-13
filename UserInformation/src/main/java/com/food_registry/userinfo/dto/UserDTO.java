package com.food_registry.userinfo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
	
	private Integer userid;
	private String userName;
	private String userPassword;
	private String address;
	private String city;
}
