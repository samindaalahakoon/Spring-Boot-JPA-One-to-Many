package com.saminda.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.saminda.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
