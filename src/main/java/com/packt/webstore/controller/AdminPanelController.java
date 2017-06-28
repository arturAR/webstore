package com.packt.webstore.controller;

import java.io.File;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;

import com.packt.webstore.model.Product;
import com.packt.webstore.service.ProductService;

@Controller
@RequestMapping("/admin")
public class AdminPanelController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String getAddNewProductForm(Model model){
		Product newProduct = new Product();
		model.addAttribute("newProduct", newProduct);
		return "addProduct";
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String processAddNewProductForm(@ModelAttribute("newProduct") @Valid Product productToBeAdded, 
			BindingResult result, HttpServletRequest request) {
		if(result.hasErrors()) {
			return "addProduct";
		}
		String[] suppressedFields = result.getSuppressedFields();
		
		if (suppressedFields.length > 0) {
			throw new RuntimeException("Próba wiązania niedozwolonych pól: " + 
						StringUtils.arrayToCommaDelimitedString(suppressedFields));
		}
		
		MultipartFile productImage = productToBeAdded.getProductImage();
		String rootDirectory = request.getSession().getServletContext().getRealPath("/");
				
		if (productImage!=null && !productImage.isEmpty()) {
		   try {
			   productImage.transferTo(new File(rootDirectory + "resources\\images\\" 
					   	+ productToBeAdded.getProductCode() + ".png"));
		   } catch (Exception e) {
			   throw new RuntimeException("Próba zapisu obrazka zakończona niepowodzeniem", e);
		   }
		}
		
	   	productService.addProduct(productToBeAdded);
		return "redirect:/products";
	}
}
