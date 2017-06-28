package com.packt.webstore.validator;

import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.validation.BindException;
import org.springframework.validation.ValidationUtils;

import com.packt.webstore.model.Product;
import com.packt.webstore.validator.ProductValidator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("test-DispatcherServlet-context.xml")
@WebAppConfiguration
public class ProductValidatorTest {
	
	@Autowired
	private ProductValidator productValidator;
	 
	@Test
	public void product_without_UnitPrice_should_be_invalid() {
		//Ustaw
		Product product = new Product();
		BindException bindException = new BindException(product, " product");

		//Wylicz
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		
		//Por�wnaj
		Assert.assertEquals(1, bindException.getErrorCount()); 
		Assert.assertTrue(bindException.getLocalizedMessage().contains("B��dna cena produktu. Cena nie mo�e by� pusta."));
	}
	
	@Test
	public void product_with_existing_productId_invalid() {
		//Ustaw
		Product product = new Product("P1234","iPhone 5s", new BigDecimal(500));
		product.setCategory("Tablet");
		
		BindException bindException = new BindException(product, " product");

		//Wylicz
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		
		//Por�wnaj
		Assert.assertEquals(1, bindException.getErrorCount()); 
		Assert.assertTrue(bindException.getLocalizedMessage().contains("Produkt o wskazanym identyfikatorze ju� istnieje."));
	}
	
	@Test
	public void a_valid_product_should_not_get_any_error_during_validation() {
		//Ustaw
		Product product = new Product("P9876","iPhone 5s", new BigDecimal(500));
		product.setCategory("Tablet");
		
		BindException bindException = new BindException(product, " product");

		//Wylicz
		ValidationUtils.invokeValidator(productValidator, product, bindException);
		
		//Por�wnaj
		Assert.assertEquals(0, bindException.getErrorCount()); 
	}

}
