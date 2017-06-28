package com.packt.webstore.service.impl;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.packt.webstore.dao.ProductDao;
import com.packt.webstore.model.Product;
import com.packt.webstore.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	@Autowired
	private ProductDao productDao;
	
	@Override
	public List<Product> getAllProducts() {
		return productDao.findAll();
	}

	@Override
	public Product getProductByCode(String productCode) {
		return productDao.findByProductCode(productCode);
	}

	@Override
	public List<Product> getProductsByCategory(String category) {
		return productDao.getProductsByCategory(category);
	}

	@Override
	public Set<Product> getProductsByFilter(Map<String, List<String>> filterParams) {
		List<Product> listOfProducts = productDao.findAll();
		Set<Product> productsByBrand = new HashSet<Product>();
		Set<Product> productsByCategory = new HashSet<Product>();

		Set<String> criterias = filterParams.keySet();
		
		if(criterias.contains("brand")) {
			for(String brandName: filterParams.get("brand")) {
				for(Product product: listOfProducts) {
					if(brandName.equalsIgnoreCase(product.getManufacturer())){
						productsByBrand.add(product);
					}
				}
			}
		}
		
		if(criterias.contains("category")) {
			for(String category: filterParams.get("category")) {
				productsByCategory.addAll(this.getProductsByCategory(category));
			}
		}
		
		productsByCategory.retainAll(productsByBrand);
		
		return productsByCategory;
	}

	@Override
	public List<Product> getProductsByManufacturer(String manufacturer) {
		return productDao.getProductsByManufacturer(manufacturer);
	}

	@Override
	public Set<Product> getProductsByPriceRange(Map<String, List<String>> filterParams) {
		List<Product> listOfProducts = productDao.findAll();
		Set<String> criterias = filterParams.keySet();
		Set<Product> productsByLowPriceRange = new HashSet<Product>();
		Set<Product> productsByHighPriceRange = new HashSet<Product>();
		Set<Product> productsByPriceRange = new HashSet<Product>();
		if(criterias.contains("low")) {
			for(String price: filterParams.get("low")) {
				for(Product product: listOfProducts) {
					if((new BigDecimal(price)).compareTo(product.getUnitPrice()) == -1){
						productsByLowPriceRange.add(product);
					}
				}
			}
		}
		if(criterias.contains("high")) {
			for(String price: filterParams.get("high")) {
				for(Product product: listOfProducts) {
					if((new BigDecimal(price)).compareTo(product.getUnitPrice()) == 1){
						productsByHighPriceRange.add(product);
					}
				}
			}
		}
		if(!productsByLowPriceRange.isEmpty() && !productsByHighPriceRange.isEmpty()) {
			productsByHighPriceRange.retainAll(productsByLowPriceRange);
			productsByPriceRange.addAll(productsByHighPriceRange);
		}else{
			productsByPriceRange.addAll(productsByHighPriceRange);
			productsByPriceRange.addAll(productsByLowPriceRange);
		}

		return productsByPriceRange;
	}

	@Override
	public Set<Product> getFilteredProducts(String productCategory, Map<String, List<String>> filterParams,
			String manufacturer) {
		List<Product> productsByCategory = getProductsByCategory(productCategory);
		List<Product> productsByManufacturer = getProductsByManufacturer(manufacturer);
		Set<Product> productsByPriceRange = getProductsByPriceRange(filterParams);
		
		productsByPriceRange.retainAll(productsByManufacturer);
		productsByPriceRange.retainAll(productsByCategory);
		
		return productsByPriceRange;
	}

	@Override
	public void addProduct(Product product) {
		productDao.save(product);
	}
	
	public void updateUnitOfProducts(Product product) {
		productDao.save(product);
	}


}
