package com.food_registry.foodCatalogue.dto;

import java.util.List;

import com.food_registry.foodCatalogue.entity.FoodItem;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FoodCataloguePage {
	private List<FoodItem> foodItemList;
	private Restaurant restaurant;
}

//This page is responsible for showing Food items in FE/JSON
//This page also has Restaurant Page, i.e., carbon copy of Restaurant Service's Entity
//We've Restaurant Page so that we can show it on FE/JSON