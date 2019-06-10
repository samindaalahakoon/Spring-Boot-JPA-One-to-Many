package com.saminda.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.saminda.model.Department;
import com.saminda.service.DepartmentService;

@Controller
public class DepartmentController {
	
	@Autowired
	DepartmentService departmentService;
	
	public void insertDepartment(String name, int dep_id){
		Department department = new Department(name, dep_id);
		departmentService.insertDepartment(department);
	}
	
	public void updateDepartment(int id,String name, int dep_id){
		Department department = new Department();
		department.setId(id);
		department.setName(name);
		department.setDep_id(dep_id);
		
		departmentService.updateDepartment(department);
		
	}
	
	public void deleteDepartment(int id){
		departmentService.deleteDepartment(id);
	}
	
	public Optional<Department> getDepartment(int id){
		return departmentService.getDepartmentByID(id);
	}

	public List<Department> getAllDepartments(){
		List<Department> departments = departmentService.getAllDepartments();
		return departments;
		
	}
}
