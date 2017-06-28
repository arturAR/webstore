package com.packt.webstore.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.dao.OrderDao;
import com.packt.webstore.dao.OrderRepository;
import com.packt.webstore.model.Order;
import com.packt.webstore.model.Product;
import com.packt.webstore.service.CartService;
import com.packt.webstore.service.OrderService;
import com.packt.webstore.service.ProductService;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private ProductService productService;
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CartService cartService;
	@Autowired
	private OrderDao orderDao;
	
	public void processOrder(String productCode, long quantity) {
		Product productByCode = productService.getProductByCode(productCode);
		
		if(productByCode.getUnitsInStock() < quantity){
			throw new IllegalArgumentException("Zbyt mało towaru. Obecna liczba sztuk w magazynie " 
					+ productByCode.getUnitsInStock());
		}
		
		productByCode.setUnitsInStock(productByCode.getUnitsInStock() - quantity);
		productService.updateUnitOfProducts(productByCode);
	}

	@Override
	public Long saveOrder(Order order) {
		Long orderId = orderRepository.saveOrder(order);
		orderDao.save(order);
		cartService.delete(order.getCart().getCartId());
		return orderId;
	}
}
