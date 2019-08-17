package com.ordermanagement.customercontroller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ordermanagement.model.customer.Customer;
import com.ordermanagement.repo.CustomerRepo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = {"Customers"})

public class CustomerController {
	@Autowired
	private CustomerRepo customerRepo;

	@RequestMapping(value = "/customers", method = RequestMethod.GET)
	@ApiOperation(value = "List of customers")
	public List<Customer> getAllCustomers() {
		return customerRepo.getAllCustomers();
	}

	@RequestMapping(value = "/customers", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value = "Add New customer")
	public String createCustomer(@RequestBody Customer customer) {
		int savedStudent = customerRepo.addNewCustomer(customer);
		if (savedStudent >= 1) {
			return "saved successfully...";
		}
		return "saved successfully...";
	}

	@RequestMapping(value = "/customers/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	@ApiOperation(value = "Delete one customer")
	public void deleteCustomer(@PathVariable Integer id) {
		customerRepo.deleteCustomer(id);
	}

}
