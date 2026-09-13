package com.food_registry.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.food_registry.order.dto.OrderDTO;
import com.food_registry.order.dto.OrderDTOFromFE;
import com.food_registry.order.service.OrderService;

import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	OrderService orderService;
	
	@PostMapping("/saveOrder")
	public ResponseEntity<OrderDTO> saveOrder(@RequestBody OrderDTOFromFE orderDetails){
		OrderDTO orderSavedToDB = orderService.saveOrderInDB(orderDetails);
		return new ResponseEntity<>(orderSavedToDB, HttpStatus.CREATED);
	}
	
	@GetMapping("/viewAllOrders")
	public ResponseEntity<List<OrderDTO>> viewAllOrders() {
	    List<OrderDTO> orders = orderService.getAllOrders();
	    return new ResponseEntity<>(orders, HttpStatus.OK);
	}

	@GetMapping("/fetchOrderById/{id}")
	public ResponseEntity<OrderDTO> fetchOrderById(@PathVariable("id") Integer id) {
	    return orderService.getOrderById(id);
	}
}
