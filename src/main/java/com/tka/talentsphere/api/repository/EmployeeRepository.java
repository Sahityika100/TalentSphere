package com.tka.talentsphere.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tka.talentsphere.api.entity.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	List<Employee> findByFirstNameContainingIgnoreCase(String name);

	List<Employee> findByHrUserId(Long hrUserId);

	List<Employee> findByFirstNameContainingIgnoreCaseAndHrUserId(String name, Long hrUserId);
}

