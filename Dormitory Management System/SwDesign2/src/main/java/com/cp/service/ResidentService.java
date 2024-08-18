package com.cp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cp.model.Resident;
import com.cp.repository.LeaseRepository;
import com.cp.repository.ResidentRepository;

@Service
public class ResidentService {
	private ResidentRepository residentRepository;

	// constructor dependency injection
	public ResidentService(ResidentRepository residentRepository) {
		super();
		this.residentRepository = residentRepository;
	}

	public List<Resident> getAllResident() {
		return (List<Resident>) this.residentRepository.findAll();
	}

	public Resident getResidentById(String id) {
		return this.residentRepository.findById(id).get();
	}

	public void deleteResidentById(String id) {
		Resident resident = this.residentRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Resident Id:" + id));
		residentRepository.delete(resident);
	}

	public void saveResident(Resident resident) {
		this.residentRepository.save(resident);
	}

}
