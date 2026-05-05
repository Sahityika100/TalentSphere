package com.tka.talentsphere.api.model;

import java.time.LocalDateTime;
import java.util.List;

public class DepartmentDTO {

	private Long id;
	private String name;
	private String description;
	private LocalDateTime createdAt;
	private List<EmployeeDTO> employees;

	public DepartmentDTO() {
	}

	public DepartmentDTO(Long id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime localDateTime) {
		this.createdAt = localDateTime;
	}
}
