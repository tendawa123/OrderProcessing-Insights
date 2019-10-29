package com.ordermanagement.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ordermanagement.model.customer.Customer;
import com.ordermanagement.repo.CustomerRepo;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepo customerRepo;

	public List<Customer> getAllCustomers() {
		List<Customer> listOfCustomers = customerRepo.getAllCustomers();
		return listOfCustomers;
	}

	public int addCustomer(Customer customer) {
		int savedStudent = customerRepo.addNewCustomer(customer);
		return savedStudent;
	}

	public int deleteCustomer(Integer id) {
		int deleted = customerRepo.deleteCustomer(id);
		return deleted;
	}
}
