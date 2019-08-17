package com.ordermanagement.employeecontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.ordermanagement.model.employee.Employee;
import com.ordermanagement.repo.EmployeeRepo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = { "Employees" })
public class EmployeeController {
	@Autowired
	private EmployeeRepo employeeRepo;

	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	@ApiOperation(value = "List of employees")
	public List<Employee> getAllEmployees() {
		return employeeRepo.getAllEmployees();
	}

	@RequestMapping(value = "/employees", method = RequestMethod.POST, produces = { "application/json" })
	@ApiOperation(value = "Add New employee")
	public String createEmployee(@RequestBody Employee employee) {
		int savedStudent = employeeRepo.addNewEmployee(employee);
		if (savedStudent >= 1) {
			return "saved successfully...";
		}
		return "saved successfully...";
	}

	@RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE, produces = { "application/json" })
	@ApiOperation(value = "Delete one employee")
	public void deleteCustomer(@PathVariable Integer id) {
		employeeRepo.deleteEmployee(id);
	}
}
