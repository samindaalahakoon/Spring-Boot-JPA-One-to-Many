package com.saminda.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.saminda.model.Department;
import com.saminda.repository.DepartmentRepository;
import com.saminda.service.DepartmentService;

@Service
public class DepartmentServiceImpl implements DepartmentService{

	@Autowired
	DepartmentRepository departmentRepository;
	
	@Override
	public void insertDepartment(Department department) {
		departmentRepository.save(department);
		
	}

	@Override
	public void updateDepartment(Department department) {
		departmentRepository.saveAndFlush(department);
		
	}

	@Override
	public void deleteDepartment(int dep_id) {
		departmentRepository.deleteById(dep_id);
		
	}

	@Override
	public List<Department> getAllDepartments() {
		return departmentRepository.findAll();
	}

	@Override
	public Optional<Department> getDepartmentByID(int dep_id) {
		return departmentRepository.findById(dep_id);
	}

}
