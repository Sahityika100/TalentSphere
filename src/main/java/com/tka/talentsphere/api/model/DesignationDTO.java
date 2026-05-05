package com.tka.talentsphere.api.model;

import java.util.List;

public class DesignationDTO {

	private Long id;
	private String title;
	private String level;
	private List<EmployeeDTO> employees;

	public DesignationDTO() {
	}

	public DesignationDTO(Long id, String title, String level) {
		this.id = id;
		this.title = title;
		this.level = level;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public List<EmployeeDTO> getEmployees() {
		return employees;
	}

	public void setEmployees(List<EmployeeDTO> employees) {
		this.employees = employees;
	}
}
