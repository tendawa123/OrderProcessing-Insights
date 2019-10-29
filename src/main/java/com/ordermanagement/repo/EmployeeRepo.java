package com.ordermanagement.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.ordermanagement.model.employee.Employee;
@Repository
public class EmployeeRepo {
	
	@Autowired
	JdbcTemplate template;
	@Transactional
	public List<Employee> getAllEmployees() {
		List<Employee> employees = template.query("SELECT * FROM ORDERMANAGER.EMPLOYEES",
				(result, rowNum) -> new Employee(result.getInt("id"), result.getString("last_name"),
						result.getString("first_name"), result.getString("email"), result.getString("avatar"),
						result.getString("job_Title"), result.getString("department"), result.getInt("manager_Id"),
						result.getString("phone"), result.getString("address1"), result.getString("address2"),
						result.getString("city"), result.getString("state"), result.getString("postal_Code"),
						result.getString("country")));
		return employees;
	}
	@Transactional
	public int addNewEmployee(Employee employee) {
		return template.update(
				"INSERT INTO EMPLOYEES (id, last_name, first_name,email,avatar,job_Title,department,manager_Id,phone,address1,address2,city,state,postal_Code,country) "
						+ "VALUES(?,?, ?,?,?,?,?,?,?,?,?,?)",
				new Object[] { employee.getId(), employee.getLastName(), employee.getFirstName(), employee.getEmail(),
						employee.getAvatar(),employee.getJobTitle(),employee.getDepartment(),employee.getManagerId(),employee.getPhone(), employee.getAddress1(), employee.getAddress2(),
						employee.getCity(), employee.getState(), employee.getPostalCode(), employee.getCountry() });
	}
	@Transactional
	public int deleteEmployee(int id) {
		String query = "DELETE FROM EMPLOYEES WHERE ID =?";
		return template.update(query, id);
	}
}
