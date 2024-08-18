package com.cp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.cp.model.Bill;
import com.cp.model.Lease;
import com.cp.service.BillService;
import com.cp.service.LeaseService;
import java.util.List;

@Controller
public class ResidentPageController {

	@Autowired
	private LeaseService leaseService;

	@Autowired
	public void setLeaseService(LeaseService leaseService) {
		this.leaseService = leaseService;
	}

	@Autowired
	private BillService billService;

	@Autowired
	public void setBillService(BillService billService) {
		this.billService = billService;
	}

	@GetMapping("/dashboard/{id}")
	public String getDashboard(@PathVariable("id") Lease lease, Model model) {
		model.addAttribute("lease", lease);
		return "dashboard-resident";
	}

	@GetMapping("/bill/{id}")
	public String getBillUser(@PathVariable("id") Lease lease, Model model) {
		List<Bill> billList = billService.getBillByLease(lease);
		Bill lastBill = leaseService.findLatestBill(lease);
		model.addAttribute("billList", billList);
		model.addAttribute("lease", lease);
		model.addAttribute("bill", lastBill);
		return "bill-resident";
	}

}
