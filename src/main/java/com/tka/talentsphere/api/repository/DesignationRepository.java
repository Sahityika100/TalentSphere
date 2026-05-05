package com.tka.talentsphere.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tka.talentsphere.api.entity.Designation;

@Repository
public interface DesignationRepository extends JpaRepository<Designation, Long> {

	List<Designation> findByHrUserId(Long long1);

}
