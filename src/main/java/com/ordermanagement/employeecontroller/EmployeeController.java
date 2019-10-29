package com.ordermanagement.employeecontroller;

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
import com.ordermanagement.model.employee.Employee;
import com.ordermanagement.service.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api")
@Api(tags = { "Employees" })
public class EmployeeController {
	public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);
	@Autowired
	private EmployeeService employeeService;

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	@ApiOperation(value = "List of employees")
	public ResponseEntity<List<Employee>> getAllEmployees() {
		List<Employee> listOfEmployees = employeeService.getAllEmployees();
		if (listOfEmployees.isEmpty()) {
			logger.error("Customers {} not found.", listOfEmployees);
			return new ResponseEntity(new CustomError("employees not found"), NOT_FOUND);
		}
		return new ResponseEntity<List<Employee>>(listOfEmployees, HttpStatus.OK);
	}

	@RequestMapping(value = "/employees", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value = "Add New employee")
	public ResponseEntity<?> createEmployee(@RequestBody Employee employee, UriComponentsBuilder ucBuilder) {
		int savedStudent = employeeService.addNewEmployee(employee);
		if (!(savedStudent >= 1)) {
			return new ResponseEntity(
					new CustomError("Unable to create a employee with name " + employee.getId() + " already exist."),
					HttpStatus.CONFLICT);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/api/employees/{id}").buildAndExpand(employee.getId()).toUri());
		return new ResponseEntity<String>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/employees/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	@ApiOperation(value = "Delete one employee")
	public ResponseEntity<?> deleteCustomer(@PathVariable Integer id) {
		int deleted = employeeService.deleteEmployee(id);
		if (!(deleted >= 1)) {
			logger.error("Unable to delete customer with id {} not found.", id);
			return new ResponseEntity(new CustomError("Unable to delete employee with id " + id + " not found."),
					HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Customer>(HttpStatus.NO_CONTENT);
	}
}
