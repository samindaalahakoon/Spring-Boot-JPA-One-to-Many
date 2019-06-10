package com.saminda.service;

import java.util.List;
import java.util.Optional;

import com.saminda.model.Department;

public interface DepartmentService {
	
	public void insertDepartment(Department department);
	
	public void updateDepartment(Department department);
	
	public void deleteDepartment(int dep_id);
	
	public List<Department> getAllDepartments();
	
	public Optional<Department> getDepartmentByID(int dep_id);
		

}
