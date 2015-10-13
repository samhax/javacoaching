package com.template.mvc.model.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.template.mvc.model.dao.EmployeeDAO;
import com.template.mvc.model.entity.Employee;

@Service
public class BusinessService {

	@Autowired
	private EmployeeDAO employeeDAO;

	public List<Employee> getEmployeeList(String name, String jobTitle, String location,  String email, String phoneNumber){
		List<Employee> employess = new ArrayList<Employee>();
		
		try {
			
			employess = employeeDAO.getEmployees(name, jobTitle, location, email, phoneNumber);
		} catch (SQLException e) {
			System.out.println("++++++ERROR++++++ :" + e.toString());
			e.printStackTrace();
		}
		return employess;
	}
	
	public List<Employee> getAdminEmployeeList(String name, String jobTitle, String location,  String email, String phoneNumber){
		List<Employee> employess = new ArrayList<Employee>();
		
		try {
			
			employess = employeeDAO.getAdminEmployees(name, jobTitle, location, email, phoneNumber);
		} catch (SQLException e) {
			System.out.println("++++++ERROR++++++ :" + e.toString());
			e.printStackTrace();
		}
		return employess;
	}
	
	public String deleteEmployee(int id, String name){
		String result = "";
		try {
			employeeDAO.deleteEmployee(id, name);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String updateEmployee(int id,String name,String jobTitle,String location,String email,String phoneNumber){
		String result = "";
		try {
			result = employeeDAO.updateEmployee(id, name, jobTitle, location, email, phoneNumber);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
	
	public String addEmployee(String name,String jobTitle,String location,String email,String phoneNumber, String userRole, String password){
		String result = "";
		try {
			result = employeeDAO.addEmployee(name, jobTitle, location, email, phoneNumber, userRole, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;
	}
}
