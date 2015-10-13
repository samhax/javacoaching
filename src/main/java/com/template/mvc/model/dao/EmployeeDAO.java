package com.template.mvc.model.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import com.template.mvc.model.dao.interfaces.EmployeeInterface;
import com.template.mvc.model.entity.Employee;

@Repository
public class EmployeeDAO implements EmployeeInterface {

	private JdbcTemplate jdbcTemplate;

	@Autowired
	public void setDataSource(DataSource datasource) {
		this.jdbcTemplate = new JdbcTemplate(datasource);
	}
	
	
	@Override
	public String addEmployee(String username, String jobTitle,
			String location, String email, String phoneNumber, String userRole,
			String password) throws SQLException {

		String sql1 = "INSERT INTO users(username,password,enabled) VALUES ('"+username+"','"+password+"', true)";
		this.jdbcTemplate.update(sql1);
		
		String sql2 = "INSERT INTO user_roles (username, role) VALUES ('"+username+"','"+userRole+"')";
		this.jdbcTemplate.update(sql2);
		
		String sql3 = "INSERT INTO employee (id,username,jobtitle,location,email,phonenumber) "
				+ "VALUES (NULL,'"+username+"','"+jobTitle+"','"+location+"','"+email+"','"+phoneNumber+"')";
		this.jdbcTemplate.update(sql3);
		
		return null;
	}
	
	@Override
	public List<Employee> getEmployees(String name, String jobTitle,
			String location, String email, String phoneNumber)
			throws SQLException {
		
		String dynamicQuery = buildAdminGetQuery(name, jobTitle, location, email, phoneNumber);
		
		List<Employee> employeeList = new ArrayList<Employee>();
		String sql = "SELECT id, username, jobtitle, location, email, phonenumber "
				+ "FROM test.employee" + dynamicQuery;
		
		employeeList = this.jdbcTemplate.query(sql, new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet rs, int rowNumber)
					throws SQLException {
				Employee employee = new Employee();
				employee.setId(Integer.parseInt(rs.getString("id")));
				employee.setName(rs.getString("username"));
				employee.setJobTitle(rs.getString("jobtitle"));
				employee.setLocation(rs.getString("location"));
				employee.setEmail(rs.getString("email"));
				employee.setPhoneNumber(rs.getString("phonenumber"));
				return employee;
			}
		});

		return employeeList;
	}

	@Override
	public List<Employee> getAdminEmployees(String name, String jobTitle,
			String location, String email, String phoneNumber)
			throws SQLException {
		
		String dynamicQuery = buildAdminGetQuery(name, jobTitle, location, email, phoneNumber);
		
		List<Employee> employeeList = new ArrayList<Employee>();

		String sql ="SELECT test.employee.id, test.employee.username, test.employee.jobtitle, "
				+ "test.employee.location, test.employee.email, test.employee.phonenumber , test.user_roles.role "
				+ "FROM test.employee "
				+ "INNER JOIN test.user_roles "
				+ "ON test.employee.username=test.user_roles.username " + dynamicQuery;

		employeeList = this.jdbcTemplate.query(sql, new RowMapper<Employee>() {
			@Override
			public Employee mapRow(ResultSet rs, int rowNumber)
					throws SQLException {
				Employee employee = new Employee();
				employee.setId(Integer.parseInt(rs.getString("id")));
				employee.setName(rs.getString("username"));
				employee.setJobTitle(rs.getString("jobtitle"));
				employee.setLocation(rs.getString("location"));
				employee.setEmail(rs.getString("email"));
				employee.setPhoneNumber(rs.getString("phonenumber"));
				return employee;
			}
		});

		return employeeList;
	}
	


	@Override
	public String updateEmployee(int id, String username, String jobTitle,
			String location, String email, String phoneNumber)
			throws SQLException {

		String dynamicQuery = buildUpdateQuery(id, username, jobTitle, location, email, phoneNumber);
		String sql = "UPDATE test.employee " + dynamicQuery + " WHERE test.employee.id="+id;
		this.jdbcTemplate.update(sql);
		
		return "Success";
	}

	@Override
	public String deleteEmployee(int id, String name) throws SQLException {
		
		String sql1 = "DELETE test.user_roles, test.employee "
				+ "FROM test.user_roles "
				+ "INNER JOIN test.employee "
				+ "ON test.user_roles.username=test.employee.username "
				+ "WHERE test.employee.id ="+id;
		
		this.jdbcTemplate.update(sql1);
		
		String sql2 = "DELETE FROM test.employee WHERE id="+id; 
		this.jdbcTemplate.update(sql2);
		
		return "Success";
	}
	

	public String buildGetQuery(String name, String jobTitle,
			String location, String email, String phoneNumber){
		
		StringBuilder query = new StringBuilder();
		List<String> vals = new ArrayList<String>();
		Map<String,String> map = new HashMap<String,String>();
		query.append(" WHERE ");
		
		map.put("username", name);
		map.put("jobTitle", jobTitle);
		map.put("location", location);
		map.put("email", email);
		map.put("phoneNumber", phoneNumber);

		
		for(Map.Entry<String, String> item: map.entrySet()){
			if(!item.getValue().equalsIgnoreCase("na")){
				query.append(item.getKey()).append("=").append("'"+item.getValue()+"'").append(" AND ");			
			}
		}	
		int index = query.lastIndexOf("AND");
		query.delete(index, index + "AND".length());
		
		return query.toString();
	}
	
	public String buildAdminGetQuery(String name, String jobTitle,
			String location, String email, String phoneNumber){
		
		StringBuilder query = new StringBuilder();
		List<String> vals = new ArrayList<String>();
		Map<String,String> map = new HashMap<String,String>();
		query.append(" WHERE ");
		
		map.put("username", name);
		map.put("jobTitle", jobTitle);
		map.put("location", location);
		map.put("email", email);
		map.put("phoneNumber", phoneNumber);

		
		for(Map.Entry<String, String> item: map.entrySet()){
			if(!item.getValue().equalsIgnoreCase("na")){
				query.append("test.employee."+item.getKey()).append("=").append("'"+item.getValue()+"'").append(" AND ");			
			}
		}	
		int index = query.lastIndexOf("AND");
		query.delete(index, index + "AND".length());
		
		return query.toString();
	}

	
	public String buildUpdateQuery(int id, String name, String jobTitle,
			String location, String email, String phoneNumber){
		
		StringBuilder query = new StringBuilder();
		List<String> vals = new ArrayList<String>();
		Map<String,String> map = new HashMap<String,String>();
		query.append(" SET ");
		
		map.put("username", name);
		map.put("jobTitle", jobTitle);
		map.put("location", location);
		map.put("email", email);
		map.put("phoneNumber", phoneNumber);

		
		for(Map.Entry<String, String> item: map.entrySet()){
			if(!item.getValue().equalsIgnoreCase("na")){
				query.append(item.getKey()).append("=").append("'"+item.getValue()+"'").append(" , ");			
			}
		}	
		int index = query.lastIndexOf(",");
		query.delete(index, index + ",".length());
		
		return query.toString();
	}


	
	

}
