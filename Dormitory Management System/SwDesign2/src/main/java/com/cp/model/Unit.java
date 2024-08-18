package com.cp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "unit")
public class Unit {

	@Id
	@Column(name = "unit_id")
	private Integer unit_id;
	@Column(name = "unit_price")
	private Integer unit_price;
	@Column(name = "unit_desc")
	private String unit_desc;
	
	@JsonIgnore
	@OneToMany(targetEntity = Bill.class, mappedBy = "unit", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Bill> bills;

	
	public Unit() {
		super();
	}

	public Unit(Integer unit_id, Integer unit_price, String unit_desc, List<Bill> bills) {
		super();
		this.unit_id = unit_id;
		this.unit_price = unit_price;
		this.unit_desc = unit_desc;
		this.bills = bills;
	}

	public Integer getUnit_id() {
		return unit_id;
	}

	public void setUnit_id(Integer unit_id) {
		this.unit_id = unit_id;
	}

	public Integer getUnit_price() {
		return unit_price;
	}

	public void setUnit_price(Integer unit_price) {
		this.unit_price = unit_price;
	}

	public String getUnit_desc() {
		return unit_desc;
	}

	public void setUnit_desc(String unit_desc) {
		this.unit_desc = unit_desc;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

	
}
