package com.cp.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cp.model.Room;
import com.cp.model.Lease;
import com.cp.model.Resident;
import com.cp.service.LeaseService;
import com.cp.service.ResidentService;
import com.cp.service.RoomService;

@Controller
public class LeaseController {

	@Autowired
	private LeaseService leaseService;

	@Autowired
	private RoomService roomService;

	@Autowired
	private ResidentService residentService;

	@Autowired
	public LeaseService getLeaseService() {
		return leaseService;
	}

	@Autowired
	public void setLeaseService(LeaseService leaseService) {
		this.leaseService = leaseService;
	}

	@Autowired
	public RoomService getRoomService() {
		return roomService;
	}

	@Autowired
	public void setRoomService(RoomService roomService) {
		this.roomService = roomService;
	}

	@Autowired
	public ResidentService getResidentService() {
		return residentService;
	}

	@Autowired
	public void setResidentService(ResidentService residentService) {
		this.residentService = residentService;
	}

	@GetMapping("/leases")
	public String showAllLease(Model model) {
		List<Lease> lease = leaseService.getAllLease();
		model.addAttribute("leases", lease);
		return "leases-owner";

	}

	@GetMapping("/add-lease")
	public String getaddLease(Model model) {
		List<Resident> res = residentService.getAllResident();
		model.addAttribute("resident", res);
		List<Room> room = roomService.getAllRoomRepository();
		model.addAttribute("room", room);
		return "add-lease-owner";
	}

	@PostMapping("/add-lease")
	public String addLease(String id_cards, String rooms, int deposit, int discount, String start_date_lease,
			String end_date_lease, String pet, int member) throws ParseException {
		SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-dd-MM");
		String lease_status = "on";

		Date stdate = formatter2.parse(start_date_lease);
		Date edate = formatter2.parse(end_date_lease);

		Room room = roomService.getRoomById(rooms).orElse(null);
		Resident resident = residentService.getResidentById(id_cards).orElse(null);
		
		if (room != null && resident != null) {
			Lease newLease = new Lease();
			newLease.setRoom(room);
			newLease.setResident(resident);
			newLease.setDeposit(deposit);
			newLease.setDiscount(discount);
			newLease.setStart_date_lease(stdate);
			newLease.setEnd_date_lease(edate);
			newLease.setPet(pet);
			newLease.setMember(member);
			newLease.setLease_status(lease_status);
			leaseService.saveLease(newLease);
			return "redirect:/leases";
		}
		return "redirect:/add-lease";
	}

	@GetMapping("/edit-lease/{id}")
	public String editLease(@PathVariable("id") Integer id, Model model) {
		List<Room> room = roomService.getAllRoomRepository();
		model.addAttribute("room", room);
		List<Resident> resident = residentService.getAllResident();
		model.addAttribute("resident", resident);
		Lease lease = leaseService.getLeaseById(id);
		model.addAttribute("lease", lease);
		return "edit-lease-owner";
	}

	@PostMapping("/edit-lease/{id}")
	public String editLease(@PathVariable("id") int id, @Validated Lease lease, BindingResult result, Model model) {
//		if (result.hasErrors()) {
//			return "leases-owner";
//		}

		Lease updateLease = leaseService.getLeaseById(id);

		roomService.setStatusToOut(updateLease.getRoom().getRoom_id());
		
		updateLease.setRoom(lease.getRoom());
		updateLease.setDeposit(lease.getDeposit());
		updateLease.setDiscount(lease.getDiscount());
		updateLease.setStart_date_lease(lease.getStart_date_lease());
		updateLease.setEnd_date_lease(lease.getEnd_date_lease());
		updateLease.setLease_status(lease.getLease_status());
		updateLease.setMember(lease.getMember());
		updateLease.setPet(lease.getPet());

		roomService.setStatusToIn(updateLease.getRoom().getRoom_id());
		leaseService.saveLease(updateLease);
		
		return "redirect:/leases";
	}

	@GetMapping("/delete-lease/{id}")
	public String deleteLeaseById(@PathVariable("id") int id, Model model) {
		leaseService.deleteLeaseById(id);
		return "redirect:/leases";
	}

}
