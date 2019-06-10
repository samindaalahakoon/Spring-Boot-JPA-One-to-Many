package com.saminda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saminda.model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{

}
