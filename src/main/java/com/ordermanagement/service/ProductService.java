package com.ordermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordermanagement.model.product.Product;
import com.ordermanagement.repo.ProductRepo;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	public List<Product> getAllProducts() {
		List<Product> listOfProducts = productRepo.getAllProducts();
		return listOfProducts;
	}

	public int addNewProduct(Product product) {
		int savedStudent = productRepo.addNewProduct(product);
		return savedStudent;
	}

	public int deleteCustomer(int id) {
		int deleted = productRepo.deleteCustomer(id);
		return deleted;
	}
}
