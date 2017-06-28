package com.packt.webstore.dao;

import com.packt.webstore.model.Cart;

public interface CartRepository {

	Cart create(Cart cart);
	Cart read(String cartId);
	void update(String cartId, Cart cart);
	void delete(String cartId);
}
