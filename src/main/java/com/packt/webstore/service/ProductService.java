package com.packt.webstore.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.packt.webstore.model.Product;

public interface ProductService {

	List<Product> getAllProducts();
	
	Product getProductByCode(String productCode);
	
	List<Product> getProductsByCategory(String category);
	
	Set<Product> getProductsByFilter(Map<String, List<String>> filterParams);
	
	List<Product> getProductsByManufacturer(String manufacturer);
	
	Set<Product> getProductsByPriceRange(Map<String, List<String>> filterParams);

	Set<Product> getFilteredProducts(String productCategory, Map<String, List<String>> filterParams, String manufacturer);
	
	void addProduct(Product product);
	
	void updateUnitOfProducts(Product product);
	
}
