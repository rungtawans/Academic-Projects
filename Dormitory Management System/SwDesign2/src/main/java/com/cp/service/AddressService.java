package com.cp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cp.model.Amphures;
import com.cp.model.Districts;
import com.cp.model.Geographies;
import com.cp.model.Provinces;
import com.cp.repository.AmphuresRepository;
import com.cp.repository.DistrictsRepository;
import com.cp.repository.GeographiesRepository;
import com.cp.repository.ProvincesRepository;

@Service
public class AddressService {

	private AmphuresRepository amphuresRepository;
	private ProvincesRepository provincesRepository;
	private GeographiesRepository geographiesRepository;
	private DistrictsRepository districtsRepository;

	// constructor dependency injection
	public AddressService(AmphuresRepository amphuresRepository, ProvincesRepository provincesRepository,
			GeographiesRepository geographiesRepository, DistrictsRepository districtsRepository) {
		super();
		this.amphuresRepository = amphuresRepository;
		this.provincesRepository = provincesRepository;
		this.geographiesRepository = geographiesRepository;
		this.districtsRepository = districtsRepository;
	}

	// get all
	public List<Geographies> getAllGeographies() {
		List<Geographies> geo = (List<Geographies>) this.geographiesRepository.findAll();
		return geo;
	}

	public List<Provinces> getAllProvinces() {
		List<Provinces> prov = (List<Provinces>) this.provincesRepository.findAll();
		return prov;
	}

	public List<Amphures> getAllAmphures() {
		List<Amphures> amph = (List<Amphures>) this.amphuresRepository.findAll();
		return amph;
	}

	public List<Districts> getAllDistricts() {
		List<Districts> dist = (List<Districts>) this.districtsRepository.findAll();
		return dist;
	}

	// get by id
	public Geographies getGeographiesById(Integer id) {
		return this.geographiesRepository.findById(id).get();
	}

	public Provinces getProvincesById(Integer id) {
		return this.provincesRepository.findById(id).get();
	}

	public Amphures getAmphuresById(Integer id) {
		return this.amphuresRepository.findById(id).get();
	}

	public Districts getDistrictsById(Integer id) {
		return this.districtsRepository.findById(id).get();
	}

}
