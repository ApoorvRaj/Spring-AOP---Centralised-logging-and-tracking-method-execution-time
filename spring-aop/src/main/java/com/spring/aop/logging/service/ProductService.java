package com.spring.aop.logging.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.spring.aop.logging.entity.Product;

@Service
public interface ProductService {

	public void addProducts();

	public void addProduct(Product product);

	public Product fetchProduct(int id);

	public List<Product> fetchAll();
}
