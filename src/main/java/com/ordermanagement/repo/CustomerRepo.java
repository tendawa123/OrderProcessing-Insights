package com.ordermanagement.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ordermanagement.model.customer.Customer;

@Repository
public class CustomerRepo {
	
	@Autowired(required = true)
	JdbcTemplate template;
	
	@Transactional
	public List<Customer> getAllCustomers() {
		List<Customer> customers = template.query("SELECT * FROM ORDERMANAGER.CUSTOMERS",
				(result, rowNum) -> new Customer(result.getInt("id"), result.getString("last_name"),
						result.getString("first_name"), result.getString("email"), result.getString("company"),
						result.getString("phone"), result.getString("address1"), result.getString("address2"),
						result.getString("city"), result.getString("state"), result.getString("postal_code"),
						result.getString("country")));
		return customers;
	}

	@Transactional
	public int addNewCustomer(Customer customer) {
		return template.update(
				"INSERT INTO CUSTOMERS (id, last_name, first_name,email,company,phone,address1,address2,city,state,postal_code,country) "
						+ "VALUES(?,?, ?,?,?,?,?,?,?,?,?,?)",
				new Object[] { customer.getId(), customer.getLast_name(), customer.getFirst_name(), customer.getEmail(),
						customer.getCompany(), customer.getPhone(), customer.getAddress1(), customer.getAddress2(),
						customer.getCity(), customer.getState(), customer.getPostal_code(), customer.getCountry() });
	}

	@Transactional
	public int deleteCustomer(int id) {
		String query = "DELETE FROM CUSTOMERS WHERE ID =?";
		return template.update(query, id);
	}

}
