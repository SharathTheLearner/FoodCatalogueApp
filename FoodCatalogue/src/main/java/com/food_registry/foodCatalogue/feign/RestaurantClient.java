package com.food_registry.foodCatalogue.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.food_registry.foodCatalogue.dto.Restaurant;

@FeignClient(name="restaurant-service")
public interface RestaurantClient {
	
	@GetMapping("/restaurant/fetchById/{id}")
	Restaurant fetchRestaurantById(@PathVariable("id") Integer id);
}
