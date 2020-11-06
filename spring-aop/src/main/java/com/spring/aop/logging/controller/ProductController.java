package com.spring.aop.logging.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.aop.logging.entity.Product;
import com.spring.aop.logging.service.ProductService;

@RestController
public class ProductController {

	@Autowired
	ProductService productService;
	
	@PostMapping
	@RequestMapping("/saveAll")
	public void saveAll() {
		productService.addProducts();
	}
	
	@PostMapping
	@RequestMapping("/saveProduct")
	public void saveProduct(@RequestBody Product product) {
		productService.addProduct(product);
	}
	
	@GetMapping
	@RequestMapping("/fetch/{id}")
	public ResponseEntity<Product> fetchProduct(@PathVariable("id") int id) {
		Product product = productService.fetchProduct(id);
		return new ResponseEntity<Product>(product, HttpStatus.OK);
	}
	
	@GetMapping
	@RequestMapping("/fetchAll")
	public ResponseEntity<List<Product>> fetchAll() {
		return new ResponseEntity<List<Product>>(productService.fetchAll(), HttpStatus.OK);
	}
	
}
