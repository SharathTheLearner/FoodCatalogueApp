package com.food_registry.foodCatalogue.mapper;

import org.springframework.stereotype.Component;

import com.food_registry.foodCatalogue.dto.FoodItemDTO;
import com.food_registry.foodCatalogue.entity.FoodItem;

@Component
public class FoodItemMapper {
//	Entity to DTO
	public FoodItemDTO toDto(FoodItem foodItem) {
		if(foodItem == null) return null;
		
		FoodItemDTO dto = new FoodItemDTO();
		
		dto.setId(foodItem.getId());
		dto.setItemName(foodItem.getItemName());
		dto.setItemDescription(foodItem.getItemDescription());
		dto.setVeg(foodItem.isVeg());
		dto.setPrice(foodItem.getPrice());
		dto.setRestaurantId(foodItem.getRestaurantId());
		dto.setQuantity(foodItem.getQuantity());
		
		return dto;
	}
	
//	DTO to Entity
	public FoodItem toEntity(FoodItemDTO dto) {
		if(dto == null) return null;
		
		FoodItem item = new FoodItem();
		
		item.setId(dto.getId());
		item.setItemName(dto.getItemName());
		item.setItemDescription(dto.getItemDescription());
		item.setVeg(dto.isVeg());
		item.setPrice(dto.getPrice());
		item.setRestaurantId(dto.getRestaurantId());
		item.setQuantity(dto.getQuantity());
		
		return item;
	}
}
