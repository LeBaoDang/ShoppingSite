package com.store.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.store.entity.Order;

import java.util.List;

public interface OrderService {
	
	public Order create(JsonNode orderData);
	
	public Order findById(Long id);

	public List<Order> findByUsername(String username);

}
