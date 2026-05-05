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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tka.talentsphere.api.model.EmployeeDTO;
import com.tka.talentsphere.api.service.EmployeeService;

@RestController
@RequestMapping("/employee")
@CrossOrigin(origins = "*")
public class EmployeeController {

	@Autowired
	private EmployeeService es;

	private Long getHrId(Authentication auth) {
		return (Long) auth.getCredentials();
	}

	@PostMapping("/add-employee")
	public EmployeeDTO addEmployee(@RequestBody EmployeeDTO empDTO, Authentication auth) {
		return es.addEmployee(empDTO, getHrId(auth));
	}

	@GetMapping("/get-all-employee")
	public List<EmployeeDTO> getAllEmployee(Authentication auth) {
		return es.getAllEmployee(getHrId(auth));
	}

	@PutMapping("/update-employee/{id}")
	public EmployeeDTO updateEmployee(@PathVariable Long id, @RequestBody EmployeeDTO empDTO, Authentication auth) {
		return es.updateEmployee(id, empDTO, getHrId(auth));
	}

	@DeleteMapping("/delete-employee/{id}")
	public String deleteEmployee(@PathVariable Long id, Authentication auth) {
		es.deleteEmployee(id, getHrId(auth));
		return "Employee Deleted Successfully " + id;
	}

	@GetMapping("/search")
	public List<EmployeeDTO> searchEmployeeByName(@RequestParam String name, Authentication auth) {
		return es.searchEmployeeByName(name, getHrId(auth));
	}
}
