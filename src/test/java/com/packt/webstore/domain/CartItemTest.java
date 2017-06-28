package com.packt.webstore.domain;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.packt.webstore.model.CartItem;
import com.packt.webstore.model.Product;

import junit.framework.Assert;

public class CartItemTest {

	private CartItem cartItem;
	
	@Before
	public void setup() {
		cartItem = new CartItem();
	}
	
	@Test
	public void cartItem_total_price_should_be_equal_to_product_unit_price_in_case_of_single_quantity() {
		Product iphone = new Product("P1234","iPhone 5s",new BigDecimal(500));
		cartItem.setProduct(iphone);
		
		BigDecimal totalPrice = cartItem.getTotalPrice();
		
		Assert.assertEquals(iphone.getUnitPrice(), totalPrice);
	}
}
