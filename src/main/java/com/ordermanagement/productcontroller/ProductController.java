package com.ordermanagement.productcontroller;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.ordermanagement.customerror.CustomError;
import com.ordermanagement.model.product.Product;
import com.ordermanagement.service.ProductService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Products" })
@RequestMapping("/api")
public class ProductController {
	public static final Logger logger = LoggerFactory.getLogger(ProductController.class);
	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	@ApiOperation(value = "List of products")
	public ResponseEntity<List<Product>> getAllProducts() {
		List<Product> listOfProducts = productService.getAllProducts();
		if (listOfProducts.isEmpty()) {
			logger.error("Products {} not found.", listOfProducts);
			return new ResponseEntity(new CustomError("Customers not found"), NOT_FOUND);
		}
		return new ResponseEntity<List<Product>>(listOfProducts, HttpStatus.OK);
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value = "Add new product")
	public ResponseEntity<?> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder) {
		int savedStudent = productService.addNewProduct(product);
		if (!(savedStudent >= 1)) {
			return new ResponseEntity(
					new CustomError("Unable to create a product with name " + product.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/products/{id}").buildAndExpand(product.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	@ApiOperation(value = "Delete one product")
	public ResponseEntity<?> deleteProduct(@PathVariable Integer id) {
		int deleted = productService.deleteCustomer(id);
		if (!(deleted >= 1)) {
			logger.error("Unable to delete customer with id {} not found.", id);
			return new ResponseEntity(new CustomError("Unable to delete product with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
	}
	
}
