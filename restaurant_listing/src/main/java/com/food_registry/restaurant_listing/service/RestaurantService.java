package com.food_registry.restaurant_listing.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.food_registry.restaurant_listing.dto.RestaurantDTO;
import com.food_registry.restaurant_listing.entity.Restaurant;
import com.food_registry.restaurant_listing.mapper.RestaurantMapper;
import com.food_registry.restaurant_listing.repo.RestaurantRepo;

import org.springframework.http.HttpStatus;

@Service
public class RestaurantService {
	@Autowired
	RestaurantRepo restaurantRepo;
		
	@Autowired
	RestaurantMapper restaurantMapper;

	
	public List<RestaurantDTO> findAllRestaurants() {
        List<Restaurant> restaurants = restaurantRepo.findAll();
//        List<RestaurantDTO> restaurantDTOList = restaurants.stream().map(restaurant -> RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(restaurant)).collect(Collectors.toList());
//        return restaurantDTOList;
        List<RestaurantDTO> restauDTOList = restaurants.stream().map(restaurantMapper::toDTO).collect(Collectors.toList());
        return restauDTOList;
    }

    public RestaurantDTO addRestaurantInDB(RestaurantDTO restaurantDTO) {
    	Restaurant restaurant = restaurantMapper.toEntity(restaurantDTO);
        Restaurant saved = restaurantRepo.save(restaurant);
        return restaurantMapper.toDTO(saved);
//        Restaurant savedRestaurant =  restaurantRepo.save(RestaurantMapper.INSTANCE.mapRestaurantDTOToRestaurant(restaurantDTO));
//        return RestaurantMapper.INSTANCE.mapRestaurantToRestaurantDTO(savedRestaurant);
    }

	public ResponseEntity<RestaurantDTO> fetchRestaurantById(Integer id) {
		// TODO Auto-generated method stub
		Optional<Restaurant> restaurant = restaurantRepo.findById(id);
		if(restaurant.isPresent()) {
			RestaurantDTO restaurantDTO = restaurantMapper.toDTO(restaurant.get());
			return new ResponseEntity<>(restaurantDTO, HttpStatus.OK);
		}
		
		return ResponseEntity.notFound().build();
		
//		return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		
		
		
//		Restaurant restaurant = restaurantRepo.findById(id)
//                .orElseThrow(() -> new RuntimeException("Restaurant not found with id: " + id));
//        return restaurantMapper.toDTO(restaurant);
		
	}

}
