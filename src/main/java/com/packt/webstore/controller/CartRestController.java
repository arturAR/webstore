package com.packt.webstore.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.model.Cart;
import com.packt.webstore.model.CartItem;
import com.packt.webstore.model.Product;
import com.packt.webstore.service.CartService;
import com.packt.webstore.service.ProductService;

@Controller
@RequestMapping(value = "rest/cart")
public class CartRestController {

	@Autowired
	private CartService cartService;
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Cart create(@RequestBody Cart cart) {
		return  cartService.create(cart);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.GET)
	public @ResponseBody Cart read(@PathVariable(value = "cartId") String cartId) {
		return cartService.read(cartId);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void update(@PathVariable(value = "cartId") String cartId, @RequestBody Cart cart) {
		cartService.update(cartId, cart);
	}

	@RequestMapping(value = "/{cartId}", method = RequestMethod.DELETE)
	@ResponseStatus(value = HttpStatus.OK)
	public void delete(@PathVariable(value = "cartId") String cartId) {
		cartService.delete(cartId);
	}
	
	@RequestMapping(value = "/add/{productCode}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.OK)
	public void addItem(@PathVariable String productCode, HttpServletRequest request) {
		String sessionId = request.getSession(true).getId();
		Cart cart = cartService.read(sessionId);
		if(cart == null) {
			cart = cartService.create(new Cart(sessionId));
		}
		
		Product product = productService.getProductByCode(productCode);
		if(product == null) {
			throw new IllegalArgumentException(new ProductNotFoundException(productCode));
		}
		
		cart.addCartItem(new CartItem(product));
		
		cartService.update(sessionId, cart);
	}
	
	@RequestMapping(value = "/remove/{productCode}", method = RequestMethod.PUT)
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void removeItem(@PathVariable String productCode, HttpServletRequest request) {
		
		String sessionId = request.getSession(true).getId();
		Cart cart = cartService.read(sessionId);
		if(cart == null) {
			cart = cartService.create(new Cart(sessionId));
		}
		
		Product product = productService.getProductByCode(productCode);
		if(product == null) {
			throw new IllegalArgumentException(new ProductNotFoundException(productCode));
		}
		
		cart.removeCartItem(new CartItem(product));
		
		cartService.update(sessionId, cart);
	}

	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST,  reason="Niepoprawne ��danie, sprawd� przesy�ane dane.")
	public void handleClientErrors(Exception ex) { }

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason="Wewn�trzny b��d serwera")
	public void handleServerErrors(Exception ex) {	}
}
