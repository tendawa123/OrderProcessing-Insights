package com.ordermanagement.customercontroller;

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
import com.ordermanagement.model.customer.Customer;
import com.ordermanagement.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Customers" })
@RequestMapping("/api")
public class CustomerController {
	public static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	@Autowired
	private CustomerService customerService;

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	@ApiOperation(value = "List of customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> listOfCustomers = customerService.getAllCustomers();
		if (listOfCustomers.isEmpty()) {
			logger.error("Customers {} not found.", listOfCustomers);
			return new ResponseEntity(new CustomError("Customers not found"), NOT_FOUND);
		}
		return new ResponseEntity<List<Customer>>(listOfCustomers, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/customers", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value = "Add New customer")
	public ResponseEntity<?> createCustomer(@RequestBody Customer customer, UriComponentsBuilder ucBuilder) {
		int savedStudent = customerService.addCustomer(customer);
		if (!(savedStudent >= 1)) {
			return new ResponseEntity(
					new CustomError("Unable to create a customer with name " + customer.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/customers/{id}").buildAndExpand(customer.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	@ApiOperation(value = "Delete one customer")
	public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
		int deleted = customerService.deleteCustomer(id);
		if (!(deleted >= 1)) {
			logger.error("Unable to delete customer with id {} not found.", id);
			return new ResponseEntity(new CustomError("Unable to delete customer with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}

}
