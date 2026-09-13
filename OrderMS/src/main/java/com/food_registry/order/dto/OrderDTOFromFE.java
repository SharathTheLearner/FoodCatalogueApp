package com.food_registry.order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//This DTO we're getting from FE

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTOFromFE {
	private List<FoodItemsDTO> foodItemsList;
	private Integer userID;
	private RestaurantDTO restaurantDTO;
}
