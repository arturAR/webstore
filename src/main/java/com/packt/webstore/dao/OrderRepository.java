package com.packt.webstore.dao;

import com.packt.webstore.model.Order;

public interface OrderRepository {

	Long saveOrder(Order order);
	
}
