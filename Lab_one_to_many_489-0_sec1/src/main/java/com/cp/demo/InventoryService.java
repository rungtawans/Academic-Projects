package com.cp.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InventoryService {
	//@Autowired
	private CategoryRepository catRepo;
	///@Autowired
	private ProductRepository productRepo;
	 
	//@Autowired //constructor dependency injection
	public InventoryService(CategoryRepository cate,ProductRepository pro) {
		this.catRepo=cate;
		this.productRepo=pro;
	}
	
	public List<Category> getAllCategory(){
		List<Category> cats= (List<Category>) this.catRepo.findAll();
		return cats;
	}
}
