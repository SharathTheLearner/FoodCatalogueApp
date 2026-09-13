package com.food_registry.foodCatalogue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food_registry.foodCatalogue.dto.FoodCataloguePage;
import com.food_registry.foodCatalogue.dto.FoodItemDTO;
import com.food_registry.foodCatalogue.service.FoodCatalogueService;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/foodCatalogue")
public class FoodCatalogueController {
	@Autowired
	FoodCatalogueService foodCatalogueService;
	
	@PostMapping("/addFoodItem")
	public ResponseEntity<FoodItemDTO> addItem(@RequestBody FoodItemDTO foodItemDTO){
		FoodItemDTO foodItemSaved = foodCatalogueService.addFoodItem(foodItemDTO);
		
		return new ResponseEntity<>(foodItemSaved, HttpStatus.CREATED);
	}
	
	@GetMapping("/fetchRestauAndFoodById/{id}")
	public ResponseEntity<FoodCataloguePage> fetchRestauDetailsWithFoodItems(@PathVariable("id") Integer restaurantId){
		FoodCataloguePage foodCataloguePage =  foodCatalogueService.fetchFoodCataloguePageDetails(restaurantId);
		return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
	}
}
