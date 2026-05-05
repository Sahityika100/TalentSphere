package com.tka.talentsphere.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tka.talentsphere.api.entity.Department;

@Repository
public interface DepatmentRepository extends JpaRepository<Department, Long> {

	List<Department> findByHrUserId(Long hrId);

}
