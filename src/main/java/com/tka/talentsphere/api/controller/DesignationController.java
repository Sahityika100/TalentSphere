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

import com.tka.talentsphere.api.model.DesignationDTO;
import com.tka.talentsphere.api.service.DesignationService;

@RestController
@RequestMapping("/designation")
@CrossOrigin(origins = "*")
public class DesignationController {

	@Autowired
	private DesignationService ds;

	private Long getHrId(Authentication auth) {
		return (Long) auth.getCredentials();
	}

	@PostMapping("/add-designation")
	public String addDesignation(@RequestBody DesignationDTO designationDTO, Authentication auth) {
		return ds.addDesignation(designationDTO, getHrId(auth));
	}

	@GetMapping("/get-all-designation")
	public List<DesignationDTO> getAllDesignation(Authentication auth) {
		return ds.getAllDesignation(getHrId(auth));
	}

	@PutMapping("/update-designation/{id}")
	public String updateDesignation(@PathVariable long id, @RequestBody DesignationDTO designationDTO, Authentication auth) {
		return ds.updateDesignation(id, designationDTO, getHrId(auth));
	}

	@DeleteMapping("/delete-designation/{id}")
	public String deleteDesignation(@PathVariable long id, Authentication auth) {
		return ds.deleteDesignation(id, getHrId(auth));
	}

}
