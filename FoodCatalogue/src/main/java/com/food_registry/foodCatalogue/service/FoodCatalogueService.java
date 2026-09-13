package com.food_registry.foodCatalogue.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.food_registry.foodCatalogue.dto.FoodCataloguePage;
import com.food_registry.foodCatalogue.dto.FoodItemDTO;
import com.food_registry.foodCatalogue.dto.Restaurant;
import com.food_registry.foodCatalogue.entity.FoodItem;
import com.food_registry.foodCatalogue.feign.RestaurantClient;
import com.food_registry.foodCatalogue.mapper.FoodItemMapper;
import com.food_registry.foodCatalogue.repo.FoodItemRepo;

@Service
public class FoodCatalogueService {
	@Autowired
	FoodItemRepo foodItemRepo;
	
	@Autowired
	FoodItemMapper foodItemMapper;
	
//	Added this url mapper from application.properties so that I don't get typo error
	@Value("${restaurant.service.url}")
	private String restaurantServiceUrl;
	
//	As soon as we have Autowired restTemplate, it becomes load balanced restTemplate
//	@Autowired
//	RestTemplate restTemplate;
	
	@Autowired
	RestaurantClient restaurantClient;

	public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
		// TODO Auto-generated method stub
		FoodItem foodItem = foodItemMapper.toEntity(foodItemDTO);
		FoodItem saved = foodItemRepo.save(foodItem);
		return foodItemMapper.toDto(saved);
	}

//	I want to follow SRP in SOLID so created multiple methods here
	public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
		List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
		Restaurant restaurant = fetchRestaurantDetailsFromRestaurantMS(restaurantId);
		return createFoodCataloguePage(foodItemList, restaurant);
	}

//	Create Food Catalogue Page with the these food item list and restaurant details
	private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
		FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
		foodCataloguePage.setFoodItemList(foodItemList);
		foodCataloguePage.setRestaurant(restaurant);
		return foodCataloguePage;
	}

//	Method to fetch restaurant details from Restaurant MicroService
	private Restaurant fetchRestaurantDetailsFromRestaurantMS(Integer restaurantId) {
//		Used getForObject on restTemplate and given the Eureka URL instead of giving localhost:9091 as it is load balanced restTemplate
//		Restaurant restaurant =  restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/"+restaurantId, Restaurant.class);
//		Restaurant restaurant =  restTemplate.getForObject(restaurantServiceUrl + "/fetchById/" + restaurantId, Restaurant.class);
//		return restaurant;
		
		return restaurantClient.fetchRestaurantById(restaurantId);
	}

//	Method to fetch Food item list
	private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
		return foodItemRepo.findByRestaurantId(restaurantId);
	}
}
