package com.tka.talentsphere.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.talentsphere.api.entity.Department;
import com.tka.talentsphere.api.entity.Designation;
import com.tka.talentsphere.api.entity.Employee;
import com.tka.talentsphere.api.entity.HrUser;
import com.tka.talentsphere.api.model.EmployeeDTO;
import com.tka.talentsphere.api.repository.DepatmentRepository;
import com.tka.talentsphere.api.repository.DesignationRepository;
import com.tka.talentsphere.api.repository.EmployeeRepository;
import com.tka.talentsphere.api.repository.HrUserRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository er;

	@Autowired
	private DepatmentRepository dr;

	@Autowired
	private DesignationRepository drr;

	@Autowired
	private HrUserRepository hrUserRepository;

	public EmployeeDTO addEmployee(EmployeeDTO empDTO, Long hrId) {
		Employee emp = new Employee();
		Department d = dr.getById(empDTO.getDepartmentId());
		Designation dest = drr.getById(empDTO.getDesignationId());
		HrUser hrUser = hrUserRepository.getById(hrId);

		emp.setFirstName(empDTO.getFirstName());
		emp.setLastName(empDTO.getLastName());
		emp.setEmail(empDTO.getEmail());
		emp.setPhoneNumber(empDTO.getPhoneNumber());
		emp.setSalary(empDTO.getSalary());
		emp.setDesignationEntity(dest);
		emp.setDepartment(d);
		emp.setHrUser(hrUser);
		d.getEmployees().add(emp);
		dest.getEmployees().add(emp);
		Employee saved = er.save(emp);
		empDTO.setId(saved.getId());
		return empDTO;
	}

	public List<EmployeeDTO> getAllEmployee(Long hrId) {
		List<Employee> empList = er.findByHrUserId(hrId);
		return empList.stream().map(emp -> {
			EmployeeDTO empDTO = new EmployeeDTO();
			empDTO.setId(emp.getId());
			empDTO.setFirstName(emp.getFirstName());
			empDTO.setLastName(emp.getLastName());
			empDTO.setEmail(emp.getEmail());
			empDTO.setPhoneNumber(emp.getPhoneNumber());
			empDTO.setSalary(emp.getSalary());
			empDTO.setDepartmentId(emp.getDepartment().getId());
			empDTO.setDesignationId(emp.getDesignationEntity().getId());
			return empDTO;
		}).toList();
	}

	public EmployeeDTO updateEmployee(Long id, EmployeeDTO empDTO, Long hrId) {
		Employee emp = er.findById(id).orElse(null);
		if (emp != null && emp.getHrUser() != null && emp.getHrUser().getId().equals(hrId)) {
			Department d = dr.getById(empDTO.getDepartmentId());
			Designation dest = drr.getById(empDTO.getDesignationId());
			emp.setFirstName(empDTO.getFirstName());
			emp.setLastName(empDTO.getLastName());
			emp.setEmail(empDTO.getEmail());
			emp.setPhoneNumber(empDTO.getPhoneNumber());
			emp.setSalary(empDTO.getSalary());
			emp.setDesignationEntity(dest);
			emp.setDepartment(d);
			er.save(emp);
			return empDTO;
		}
		return null;
	}

	public Long deleteEmployee(Long id, Long hrId) {
		Employee emp = er.findById(id).orElse(null);
		if (emp != null && emp.getHrUser() != null && emp.getHrUser().getId().equals(hrId)) {
			er.deleteById(id);
		}
		return id;
	}

	public List<EmployeeDTO> searchEmployeeByName(String name, Long hrId) {
		List<Employee> emplist = er.findByFirstNameContainingIgnoreCaseAndHrUserId(name, hrId);
		return emplist.stream().map(emp -> {
			EmployeeDTO dto = new EmployeeDTO();
			dto.setId(emp.getId());
			dto.setFirstName(emp.getFirstName());
			dto.setLastName(emp.getLastName());
			dto.setEmail(emp.getEmail());
			dto.setPhoneNumber(emp.getPhoneNumber());
			dto.setSalary(emp.getSalary());
			dto.setDesignationId(emp.getDesignationEntity().getId());
			dto.setDepartmentId(emp.getDepartment().getId());
			return dto;
		}).toList();
	}
}
