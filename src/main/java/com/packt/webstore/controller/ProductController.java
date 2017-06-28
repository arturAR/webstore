package com.packt.webstore.controller;


import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.packt.webstore.exception.NoProductsFoundCategoryException;
import com.packt.webstore.exception.ProductNotFoundException;
import com.packt.webstore.model.Product;
import com.packt.webstore.service.ProductService;

@Controller
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	//@Autowired
	//private ProductValidator productValidator;

	@RequestMapping
	public String list(Model model) {
		model.addAttribute("products", productService.getAllProducts());
		return "products";
	}
	
	@RequestMapping("/all")
	public ModelAndView allProducts(Model model) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("products", productService.getAllProducts());
		modelAndView.setViewName("products");
		return modelAndView;
	}
	
	@RequestMapping("/{category}")
	public String getProductsByCategory(@PathVariable("category") String productCategory, Model model) {
		List<Product> products = productService.getProductsByCategory(productCategory);
		if(products == null || products.isEmpty()) {
			throw new NoProductsFoundCategoryException();
		}
		model.addAttribute("products", products);
		return "products";
	}
	
	@RequestMapping("/filter/{ByCriteria}")
	public String getProductsByFilter(@MatrixVariable(pathVar="ByCriteria") Map<String,List<String>> filterParams, Model model) {
		model.addAttribute("products", productService.getProductsByFilter(filterParams));
		return "products";
	}
	
	@RequestMapping("/product")
	public String getProductById(@RequestParam("code") String productCode, Model model) {
		model.addAttribute("product", productService.getProductByCode(productCode));
		return "product";
	}
	
	@RequestMapping("/{category}/{ByCriteria}")
	public String filterProducts(@PathVariable("category") String productCategory, 
								 @MatrixVariable(pathVar="ByCriteria") Map<String, List<String>> filterParams,
								 @RequestParam("manufacturer") String manufacturer, 
								 Model model) {
		model.addAttribute("products", productService.getFilteredProducts(productCategory, filterParams, manufacturer));
		
		return "products";
	}
	
	@InitBinder
	public void initialiseBinder(WebDataBinder binder) {
		binder.setAllowedFields("productCode","name","unitPrice","description","manufacturer","category","unitsInStock", 
						"condition", "productImage", "language");
		//binder.setValidator(productValidator);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handleError(HttpServletRequest req, ProductNotFoundException exception) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("invalidProductCode", exception.getProductCode());
		mav.addObject("exception", exception);
		mav.addObject("url", req.getRequestURI() + "?" + req.getQueryString());
		mav.setViewName("productNotFound");
		return mav;
	}
	
	@RequestMapping("/invalidPromoCode")
	public String invalidPromoCode() {
		return "invalidPromoCode";
	}
}

