package com.template.mvc.model.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.template.mvc.model.entity.Employee;

public interface EmployeeInterface {

	public List<Employee> getEmployees(String username, String jobTitle,
			String location, String email, String phoneNumber)
			throws SQLException;
	
	public List<Employee> getAdminEmployees(String username, String jobTitle,
			String location, String email, String phoneNumber)
			throws SQLException;
	
	public String updateEmployee(int id, String username, String jobTitle,
			String location, String email, String phoneNumber)
			throws SQLException;
	
	public String addEmployee(String username, String jobTitle,
			String location, String email, String phoneNumber, String userRole, String password)
			throws SQLException;
	
	public String deleteEmployee(int id, String name) throws SQLException;
}
