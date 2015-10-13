package com.template.mvc.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.template.mvc.model.entity.Employee;
import com.template.mvc.model.entity.Employees;
import com.template.mvc.model.service.BusinessService;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.ws.rs.core.Response;
import javax.ws.rs.*;


@Controller
public class MainController {

	@Autowired
	BusinessService businessService;

	@XmlElementWrapper(name = "employees")
	@XmlElement(name = "employee")
	public List<Employee> employeeList;

	@RequestMapping(value = { "/", "/home**" }, method = RequestMethod.GET)
	public ModelAndView defaultPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title",
				"Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!");
		model.setViewName("home");
		return model;

	}

	@RequestMapping(value = { "/welcome**" }, method = RequestMethod.GET)
	public ModelAndView userPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title",
				"Spring Security Login Form - Database Authentication");
		model.addObject("message", "This is default page!");
		model.setViewName("hello");
		return model;

	}

	@RequestMapping(value = "/admin**", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "Employee Admin page");
		model.setViewName("admin");

		return model;

	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", "Invalid name or password");
		}

		if (logout != null) {
			model.addObject("msg", "You've been logged out successfully.");
		}
		model.setViewName("login");

		return model;

	}

	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {

		ModelAndView model = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext()
				.getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("username", userDetail.getUsername());

		}

		model.setViewName("403");
		return model;

	}
	
	
	@RequestMapping(value = "/addEmployee/{name}/{jobTitle}/{location}/{email}/{phoneNumber}/{userRole}/{password}" , method= RequestMethod.GET)
	public Response addEmployee(@PathVariable String name,
			@PathVariable String jobTitle, @PathVariable String location,
			@PathVariable String email, @PathVariable String phoneNumber ,@PathVariable String userRole, @PathVariable String password) {

		businessService.addEmployee(name, jobTitle, location, email, phoneNumber, userRole, password);
		
		return Response.ok().entity("Success").build();
	}
	

	@RequestMapping(value = "/getEmployees/{name}/{jobTitle}/{location}/{email}/{phoneNumber}")
	public @ResponseBody Employees getEmployees(@PathVariable String name,
			@PathVariable String jobTitle, @PathVariable String location,
			@PathVariable String email, @PathVariable String phoneNumber) {

		employeeList = businessService.getEmployeeList(name, jobTitle,
				location, email, phoneNumber);

		Employees result = new Employees(employeeList);

		return result;
	}
	
	@RequestMapping(value = "/getAdminEmployees/{name}/{jobTitle}/{location}/{email}/{phoneNumber}")
	public @ResponseBody Employees getAdminEmployees(@PathVariable String name,
			@PathVariable String jobTitle, @PathVariable String location,
			@PathVariable String email, @PathVariable String phoneNumber) {

		employeeList = businessService.getAdminEmployeeList(name, jobTitle,
				location, email, phoneNumber);

		Employees result = new Employees(employeeList);

		return result;
	}
	
	@RequestMapping(value = "/deleteEmployee/{id}/{name}" , method= RequestMethod.GET)
	public Response  getEmployees(@PathVariable int id, @PathVariable String name) {

		businessService.deleteEmployee(id, name);

		return Response.ok().entity("Success").build();
	}
	
	
	@RequestMapping(value = "/updateEmployee/{id}/{name}/{jobTitle}/{location}/{email}/{phoneNumber}" , method= RequestMethod.GET)
	public Response  updateEmployee(@PathVariable int id, @PathVariable String name,
			@PathVariable String jobTitle, @PathVariable String location,
			@PathVariable String email, @PathVariable String phoneNumber) {

		businessService.updateEmployee(id,name, jobTitle,
			location, email, phoneNumber);


		return Response.ok().entity("Success").build();
	}
	


}