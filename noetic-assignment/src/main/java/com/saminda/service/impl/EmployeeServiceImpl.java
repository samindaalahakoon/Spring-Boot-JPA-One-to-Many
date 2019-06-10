package com.saminda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saminda.model.Employee;
import com.saminda.repository.EmployeeRepository;
import com.saminda.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	@Autowired
	EmployeeRepository employeeRepository;
	
	@Override
	public void insertEmployee(Employee employee) {
		employeeRepository.save(employee);
		
	}

	@Override
	public void updateEmployee(Employee employee) {
		employeeRepository.saveAndFlush(employee);
		
	}

	@Override
	public void deleteEmployee(int emp_id) {
		employeeRepository.deleteById(emp_id);
		
	}

	@Override
	public List<Employee> getAllEmployees() {
		System.out.println("::::::: Employee Service Impl :::::::");
		System.out.println("::::::: Employee Service findAll ::::::: " + employeeRepository.findAll());
		return employeeRepository.findAll();
	}

	@Override
	public Optional<Employee> getEmployeeByID(int emp_id) {
		return employeeRepository.findById(emp_id);
	}

}
