package com.cp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.cp.model.Lease;
import com.cp.service.BillService;
import com.cp.service.LeaseService;

@Controller
public class PageController {
	
	@Autowired
	private LeaseService leaseService;

	@Autowired
	public void setLeaseService(LeaseService leaseService) {
		this.leaseService = leaseService;
	}

	@GetMapping("/")
	public String showLoginForm(Model model) {
		model.addAttribute("loginForm", new Lease());
		return "index";
	}

	@GetMapping("/dashboard-owner")
	public String getDashboardOwner() {
		return "dashboard-owner";
	}
	
	@PostMapping("/")
	public String processLoginForm(String username, String password) {
		List<Lease> leases = leaseService.getAllLease();

		for (Lease l : leases) {
			if ("on".equals(l.getLease_status())) {
				if (username.equals(l.getRoom().getRoom_id())
						&& password.equals(l.getResident().getId_card())) {
					String redirectUrl = "/dashboard/" + l.getLease_id();
		            return "redirect:" + redirectUrl;
				}
			}
		}
		if (username.equals("root") && password.equals("1234")) {
			return "redirect:/dashboard-owner";
		}
		return "redirect:";
	}

}
