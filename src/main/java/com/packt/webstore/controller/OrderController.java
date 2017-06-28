package com.packt.webstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.packt.webstore.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;

	@RequestMapping("/order/{code}/{amount}")
	public String process(@PathVariable("code") String productCode, @PathVariable("amount") int amount, Model model) {
		orderService.processOrder(productCode, amount);
		return "redirect:/products";
	}
}
