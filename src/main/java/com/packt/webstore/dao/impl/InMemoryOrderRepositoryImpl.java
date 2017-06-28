package com.packt.webstore.dao.impl;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

import com.packt.webstore.dao.OrderRepository;
import com.packt.webstore.model.Order;

@Repository
public class InMemoryOrderRepositoryImpl implements OrderRepository{

	private Map<Long, Order> listOfOrders;
	private long nextOrderId;
	
	public InMemoryOrderRepositoryImpl() {
		listOfOrders = new HashMap<Long, Order>();
		nextOrderId = 1000;
	}

	public Long saveOrder(Order order) {
		order.setId(getNextOrderId());
		listOfOrders.put(order.getId(), order);
		return order.getId();
	}

	private synchronized long getNextOrderId() {
		return nextOrderId++;
	}
}
