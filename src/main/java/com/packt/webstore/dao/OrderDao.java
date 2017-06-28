package com.packt.webstore.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.packt.webstore.model.Order;

public interface OrderDao extends JpaRepository<Order, Long> {
	
}
