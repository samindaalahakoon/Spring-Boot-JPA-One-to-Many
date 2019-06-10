package com.saminda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.saminda.model.Department;
import com.saminda.model.Employee;
import com.saminda.service.EmployeeService;

@Controller
public class EmployeeController {
	
	@Autowired
	EmployeeService employeeService;
	
	public void insertEmployee(String firstname, String lastname, int employeeID, Department dep_id){
		Employee employee = new Employee(firstname, lastname, employeeID, dep_id);
		
		employeeService.insertEmployee(employee);
	}

	public void updateEmployee(int id, String firstname, String lastname, int emp_id, Department dep_id){
		Employee employee = new Employee();
		employee.setId(id);
		employee.setFirstname(firstname);
		employee.setLastname(lastname);
		employee.setEmployeeID(emp_id);
		employee.setDepartment(dep_id);
		
		employeeService.updateEmployee(employee);
	}
	
	public void deleteEmployee(int id){
		employeeService.deleteEmployee(id);
	}
	
	public Optional<Employee> getEmployee(int emp_id){
		return employeeService.getEmployeeByID(emp_id);
	}
	
	public List<Employee> getAllEmployees(){
		System.out.println("::::::: Employee COntroller :::::::");
		List<Employee> employees = employeeService.getAllEmployees();
		System.out.println("::::::: Employee COntroller employees:::::::" + employees);
		return employees;
	}
}
