package com.cp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails.Address;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cp.model.Amphures;
import com.cp.model.Bill;
import com.cp.model.Districts;
import com.cp.model.Lease;
import com.cp.model.Provinces;
import com.cp.model.Resident;
import com.cp.service.AddressService;
import com.cp.service.ResidentService;

@Controller
public class ResidentController {

	@Autowired
	private ResidentService residentService;

	@Autowired
	private AddressService addressService;

	@Autowired
	public ResidentController(ResidentService residentService, AddressService addressService) {
		super();
		this.residentService = residentService;
		this.addressService = addressService;
	}

	@Autowired
	public void setAddressService(AddressService addressService) {
		this.addressService = addressService;
	}

	@RequestMapping("/residents_json")
	@ResponseBody
	public List<Resident> getResidentList() {
		List<Resident> resi = residentService.getAllResident();
		return resi;
	}

	@GetMapping("/residents")
	public String showAllResidents(Model model) {
		List<Resident> residents = residentService.getAllResident();
		model.addAttribute("residents", residents);
		return "residents-owner";

	}

	@GetMapping("/add-resident")
	public String getResidentList(Model model) {
		
		List<Amphures> amphuresList = addressService.getAllAmphures();
		List<Districts> districtsList = addressService.getAllDistricts();
		List<Provinces> provincesList = addressService.getAllProvinces();
 
		model.addAttribute("amphures", amphuresList);
		model.addAttribute("districts", districtsList);
		model.addAttribute("provinces", provincesList);
		return "add-resident-owner";
	}

	@PostMapping("/add-resident")
	public String addResident(String id_card, String first_name, String last_name, String email, String phone,
			String address, Districts districts, BindingResult result) {

		Resident r = new Resident(id_card, first_name, last_name, email, phone, address, districts);
		if (result.hasErrors()) {
			return "add-resident";
		}
		residentService.saveResident(r);
		return "redirect:/residents";
	}

	@GetMapping("/edit-resident/{id}")
	public String getEditResident(@PathVariable("id") String id, Model model) {
		List<Provinces> provinces = addressService.getAllProvinces();
		List<Amphures> amphures = addressService.getAllAmphures();
		List<Districts> districts = addressService.getAllDistricts();
		model.addAttribute("provinces", provinces);
		model.addAttribute("amphures", amphures);
		model.addAttribute("districts", districts);
		Resident resident = residentService.getResidentById(id);
		model.addAttribute("resident", resident);
		return "edit-resident-owner";
	}

	@PostMapping("/edit-resident/{id}")
	public String editResident(@PathVariable("id") String id, @Validated Resident resident, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			return "residents-owner";
		}

		Resident updateResident = residentService.getResidentById(id);

		updateResident.setFirst_name(resident.getFirst_name());
		updateResident.setLast_name(resident.getLast_name());
		updateResident.setEmail(resident.getEmail());
		updateResident.setPhone(resident.getPhone());
		updateResident.setAddress(resident.getAddress());
		updateResident.setDistricts(resident.getDistricts());

		residentService.saveResident(updateResident);
		
		return "redirect:/residents";
	}

	@GetMapping("/delete-resident/{id}")
	public String deleteResident(@PathVariable("id") String id, Model model) {
		residentService.deleteResidentById(id);
		return "redirect:/residents";
	}
}
