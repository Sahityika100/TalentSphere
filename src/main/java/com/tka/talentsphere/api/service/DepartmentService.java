package com.tka.talentsphere.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.tka.talentsphere.api.entity.Department;
import com.tka.talentsphere.api.entity.HrUser;
import com.tka.talentsphere.api.model.DepartmentDTO;
import com.tka.talentsphere.api.repository.DepatmentRepository;
import com.tka.talentsphere.api.repository.HrUserRepository;

@Service
public class DepartmentService {

	@Autowired
	private DepatmentRepository dr;
	
	@Autowired
	private HrUserRepository hrUserRepository;

	
	public String addDepartment(DepartmentDTO departmentDTO, Long hrId) {
		HrUser hrUser = hrUserRepository.getById(hrId);
		Department department = new Department();
		department.setName(departmentDTO.getName());
		department.setDescription(departmentDTO.getDescription());
		department.setHrUser(hrUser);
		dr.save(department);
		System.out.println("Department added successfully" + departmentDTO);
		return "Department added Successfully..";
	}

	public List<DepartmentDTO> getDepartment(Long hrId) {
		List<Department> departments = dr.findByHrUserId(hrId);
		List<DepartmentDTO> departmentDTOs = new ArrayList<>();
		for (Department department : departments) {
			DepartmentDTO departmentDTO = new DepartmentDTO();
			departmentDTO.setName(department.getName());
			departmentDTO.setDescription(department.getDescription());
			departmentDTO.setId(department.getId());
			departmentDTO.setCreatedAt(department.getCreatedAt());
			departmentDTOs.add(departmentDTO);
		}
		return departmentDTOs;
	}

	public String getDeptById(Long id, DepartmentDTO departmentDTO, Long hrId) {
		Department dept = dr.getById(id);
		if (dept.getHrUser().getId() != hrId) {
			return "Unauthorized Access..";
		}
		else {
			dept.setName(departmentDTO.getName());
			dept.setDescription(departmentDTO.getDescription());
			dr.save(dept);
			return "Department Updated Successfully..";
		}
	}

	public String deleteById(Long id, Long hrId) {
		Department dept = dr.getById(id);
		if (dept.getHrUser().getId() != hrId) {
			return "Unauthorized Access..";
		}
		else {
			dr.deleteById(id);
			return "Department Deleted Successfully..";
		}
	}
}
