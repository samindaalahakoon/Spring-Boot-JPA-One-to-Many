package com.saminda.service;

import java.util.List;
import java.util.Optional;


import com.saminda.model.Employee;

public interface EmployeeService {
	
	public void insertEmployee(Employee employee);
	
	public void updateEmployee(Employee employee);
	
	public void deleteEmployee(int emp_id);
	
	public List<Employee> getAllEmployees();
	
	public Optional<Employee> getEmployeeByID(int emp_id);

}
