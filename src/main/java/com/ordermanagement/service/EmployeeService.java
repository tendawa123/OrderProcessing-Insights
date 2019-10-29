package com.ordermanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ordermanagement.model.employee.Employee;
import com.ordermanagement.repo.EmployeeRepo;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepo employeeRepo;

	public List<Employee> getAllEmployees() {
		List<Employee> listOfEmployees = employeeRepo.getAllEmployees();
		return listOfEmployees;
	}

	public int addNewEmployee(Employee employee) {
		int savedStudent = employeeRepo.addNewEmployee(employee);
		return savedStudent;
	}

	public int deleteEmployee(Integer id) {
		int deleted = employeeRepo.deleteEmployee(id);
		return deleted;
	}

}
