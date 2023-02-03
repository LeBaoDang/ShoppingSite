package com.store.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.entity.Order;
import com.store.entity.OrderDetail;
import com.store.repository.OrderDetailRepo;
import com.store.repository.OrderRepo;
import com.store.service.OrderService;
import com.store.service.impl.exception.ResourceNotFoundException;


@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	OrderRepo orderRepo;
	
	@Autowired
	OrderDetailRepo orderDetailRepo;
	
	@Override
	@Transactional(rollbackOn = { Exception.class, Throwable.class })
	public Order create(JsonNode orderData) {

		ObjectMapper mapper = new ObjectMapper();
		
		Order order = mapper.convertValue(orderData, Order.class);
		orderRepo.save(order);
		
		TypeReference<List<OrderDetail>> type = new TypeReference<List<OrderDetail>>() {};
		
		List<OrderDetail> details = mapper.convertValue(orderData.get("orderDetails"), type)
				.stream().peek(d -> d.setOrder(order)).collect(Collectors.toList());
		
		orderDetailRepo.saveAll(details);
		
		return order;
	}

	@Override
	public Order findById(Long id) {
		
		Optional<Order> optionOrder = orderRepo.findById(id);
		
		if(!optionOrder.isPresent()) {
			throw new ResourceNotFoundException(String.format("order.not.found.with.id:%s", id));
		}
		
		return orderRepo.findById(id).get();
	}

	@Override
	public List<Order> findByUsername(String username) {

		List<Order> optionalOrder = orderRepo.findByUsername(username);

		if(optionalOrder.isEmpty()){
			throw new ResourceNotFoundException(String.format("list.order.not.found.with.username:%s", username));
		}

		return orderRepo.findByUsername(username);
	}

}
