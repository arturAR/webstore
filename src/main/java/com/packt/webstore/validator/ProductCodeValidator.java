package com.packt.webstore.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.model.Product;
import com.packt.webstore.service.ProductService;

public class ProductCodeValidator implements ConstraintValidator<ProductCode, String>{
	
	@Autowired
	private ProductService productService;

	public void initialize(ProductCode constraintAnnotation) {
		
	}

	public boolean isValid(String value, ConstraintValidatorContext context) {
		Product product;
		try {
			product = productService.getProductByCode(value);
			
		} catch (ProductNotFoundException e) {
			return true;
		}
		if(product != null) {
			return false;
		}
		
		return true;
	}
	
}
