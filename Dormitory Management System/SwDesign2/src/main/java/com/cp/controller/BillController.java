package com.cp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cp.model.Bill;
import com.cp.service.BillService;

@Controller
public class BillController {
	
	@Autowired
	private BillService billService;

	@Autowired
	public void setBillService(BillService billService) {
		this.billService = billService;
	}
	
	@RequestMapping("/bill_list_json") 
	@ResponseBody
	public List<Bill> getBillList(){
		List<Bill> bill =  billService.getBillAll();
		return bill;
	}
	
	@GetMapping("/bills")
	public String getBillAll(Model model) {
		List<Bill> billList = billService.getBillAll();
		model.addAttribute("bills", billList);     
		return "bill-owner";
	}
	
	

}
