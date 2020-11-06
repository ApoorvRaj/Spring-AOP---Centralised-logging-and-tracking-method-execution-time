package com.spring.aop.logging.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.aop.logging.entity.Product;
import com.spring.aop.logging.repo.ProductRepository;
import com.spring.aop.logging.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{

	@Autowired
	ProductRepository productRepository;
	
	public void addProducts() {
		for(int i=0;i<100;i++) {
			Product product = new Product();
			product.setDescription("description_"+i);
			product.setName("name_"+i);
			product.setType("normal");
			productRepository.save(product);
		}
	}

	@Override
	public void addProduct(Product product) {
		productRepository.save(product);
	}

	@Override
	public Product fetchProduct(int id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		if(optionalProduct.isPresent())
			return optionalProduct.get();
		return null;
	}

	@Override
	public List<Product> fetchAll() {
		return productRepository.findAll();
	}
	
}
