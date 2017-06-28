package com.packt.webstore.service;

import com.packt.webstore.model.Order;

public interface OrderService {

	void processOrder(String  productCode, long quantity);
	Long saveOrder(Order order);
}
