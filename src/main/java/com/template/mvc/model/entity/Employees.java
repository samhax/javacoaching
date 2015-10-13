package com.template.mvc.model.entity;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "employees")
public class Employees {
	
	 @XmlElement(name = "employee", type = Employee.class)
	    private List<Employee> employees = new ArrayList<Employee>();

	    public Employees(){
	    }

	    public Employees(List<Employee> employees) {
	        this.employees = employees;
	    }

	    public List<Employee> getEmployees() {
	        return employees;
	    }

	    public void setEmployees(List<Employee> employees) {
	        this.employees = employees;
	    }

}
