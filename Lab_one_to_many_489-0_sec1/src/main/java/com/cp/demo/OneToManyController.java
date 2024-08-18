package com.cp.demo;
 import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OneToManyController {
	
//	just InventoryService 
	@Autowired
	private  InventoryService invSer;
	
	@Autowired //setter dependency injection
	public void setInventoryService(InventoryService invser) {
		this.invSer=invser;
	}
	 
//	@GetMapping("/categoryList") 
	@RequestMapping("/categoryList") 
	@ResponseBody
	public List<Category> getCatoryList(){
		List<Category> categorys =  invSer.getAllCategory();
		return categorys;
	}
}
