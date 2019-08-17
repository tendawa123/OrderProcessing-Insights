package com.ordermanagement.productcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ordermanagement.model.product.Product;
import com.ordermanagement.repo.ProductRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Products" })
public class ProductController {
	@Autowired
	private ProductRepo productRepo;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	@ApiOperation(value = "List of products")
	public List<Product> getAllProducts() {
		return productRepo.getAllProducts();
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value = "Add new product")
	public String createProduct(@RequestBody Product product) {
		int savedStudent = productRepo.addNewProduct(product);
		if (savedStudent >= 1) {
			return "saved successfully...";
		}
		return "saved successfully...";
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	@ApiOperation(value = "Delete one product")
	public void deleteProduct(@PathVariable Integer id) {
		productRepo.deleteCustomer(id);
	}

}
