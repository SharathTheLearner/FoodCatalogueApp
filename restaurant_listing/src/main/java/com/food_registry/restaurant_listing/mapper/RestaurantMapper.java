package com.food_registry.restaurant_listing.mapper;

import org.springframework.stereotype.Component;

import com.food_registry.restaurant_listing.dto.RestaurantDTO;
import com.food_registry.restaurant_listing.entity.Restaurant;

@Component
public class RestaurantMapper {
	
//	Entity to DTO
	public RestaurantDTO toDTO(Restaurant restaurant) {
        if (restaurant == null) return null;

        RestaurantDTO dto = new RestaurantDTO();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setAddress(restaurant.getAddress());
        dto.setCity(restaurant.getCity());
        dto.setRestaurantDescription(restaurant.getRestaurantDescription());
        return dto;
    }
	
//	DTO to Entity
	public Restaurant toEntity(RestaurantDTO dto) {
        if (dto == null) return null;

        Restaurant restaurant = new Restaurant();
        restaurant.setId(dto.getId());
        restaurant.setName(dto.getName());
        restaurant.setAddress(dto.getAddress());
        restaurant.setCity(dto.getCity());
        restaurant.setRestaurantDescription(dto.getRestaurantDescription());
        return restaurant;
    }
	

}
