package com.food_registry.order.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.food_registry.order.entity.Order;

@Repository
public interface OrderRepo extends MongoRepository<Order, Integer> {

}
