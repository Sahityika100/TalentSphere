package com.tka.talentsphere.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tka.talentsphere.api.model.DepartmentDTO;
import com.tka.talentsphere.api.service.DepartmentService;

@RestController
@RequestMapping("/department")
@CrossOrigin(origins = "*")
public class DepartmentController {

	@Autowired
	private DepartmentService departmentService;

	private Long getHrId(Authentication auth) {
		return (Long) auth.getCredentials();
	}
	
	@PostMapping("/add-department")
	public String addDepartment(@RequestBody DepartmentDTO departmentDTO, Authentication auth) {
		return departmentService.addDepartment(departmentDTO, getHrId(auth));
		
	}
	@GetMapping("/get-department")
	public List<DepartmentDTO> getDepartment(Authentication auth){
		return departmentService.getDepartment(getHrId(auth));
	}
	
	@PutMapping("/update-department/{id}")
	public String getDepartmentById(@PathVariable Long id,@RequestBody DepartmentDTO departmentDTO, Authentication auth) {
		return departmentService.getDeptById(id,departmentDTO, getHrId(auth));
	}
	
	@DeleteMapping("delete-department/{id}")
	public String deleteDeptById(@PathVariable Long id, Authentication auth) {
		return departmentService.deleteById(id, getHrId(auth));
	}
}
