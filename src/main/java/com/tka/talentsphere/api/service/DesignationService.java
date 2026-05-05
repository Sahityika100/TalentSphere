package com.tka.talentsphere.api.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tka.talentsphere.api.entity.Designation;
import com.tka.talentsphere.api.model.DesignationDTO;
import com.tka.talentsphere.api.repository.DesignationRepository;
import com.tka.talentsphere.api.repository.HrUserRepository;

@Service
public class DesignationService {

	@Autowired
	private DesignationRepository dr;
	
	@Autowired
	private HrUserRepository hrUserRepository;

	public String addDesignation(DesignationDTO designationDTO, Long hrId) {
		Designation designation = new Designation();
		designation.setTitle(designationDTO.getTitle());
		designation.setLevel(designationDTO.getLevel());
		designation.setHrUser(hrUserRepository.getById(hrId));
		dr.save(designation);
		return "designation added Successfully..";
	}

	public List<DesignationDTO> getAllDesignation(Long long1) {
		List<Designation> dest = new ArrayList<Designation>();
		dest = dr.findByHrUserId(long1);
		List<DesignationDTO> destDTO = new ArrayList<DesignationDTO>();
		for (Designation dest1 : dest) {
			DesignationDTO d = new DesignationDTO();
			d.setId(dest1.getId());
			d.setTitle(dest1.getTitle());
			d.setLevel(dest1.getLevel());
			destDTO.add(d);
		}
		return destDTO;
	}

	public String updateDesignation(long id, DesignationDTO destDTO, Long hrId) {
		Designation dest = dr.getById(id);
		dest.setTitle(destDTO.getTitle());
		dest.setLevel(destDTO.getLevel());
		dest.setHrUser(hrUserRepository.getById(hrId));
		dr.save(dest);
		return "designation updated Successfully..";
	}

	public String deleteDesignation(long id, Long hrId) {
		Designation dest = dr.getById(id);
		if(dest.getHrUser().getId() == hrId) {
			dr.delete(dest);
			return "designation deleted Successfully..";
		}
		return "designation not found for this hr user..";
	}
}
