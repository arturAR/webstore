package com.packt.webstore.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.packt.webstore.model.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {

    
	List<Product> findAll();
	
	Product findByProductCode(String productCode);
	
	List<Product> getProductsByCategory(String category);
	
	List<Product> getProductsByManufacturer(String manufacturer);
	
}



