package com.packt.webstore.exception;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 3935230281455340039L;
	private String productCode;
	
	public ProductNotFoundException(String productCode) {
		this.productCode = productCode;
	}
	
	public String getProductCode() {
		return productCode;
	}

}
