package com.cp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="amphures")
public class Amphures {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String name_th;
	private String name_en;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "province_id")
	private Provinces provinces;
	
	@JsonIgnore
	@OneToMany(targetEntity=Districts.class, mappedBy="amphures",
    		cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Districts> districts;

	
	public Amphures() {
		super();
	}

	public Amphures(Integer id, String code, String name_th, String name_en, Provinces provinces,
			List<Districts> districts) {
		super();
		this.id = id;
		this.code = code;
		this.name_th = name_th;
		this.name_en = name_en;
		this.provinces = provinces;
		this.districts = districts;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName_th() {
		return name_th;
	}

	public void setName_th(String name_th) {
		this.name_th = name_th;
	}

	public String getName_en() {
		return name_en;
	}

	public void setName_en(String name_en) {
		this.name_en = name_en;
	}

	public Provinces getProvinces() {
		return provinces;
	}

	public void setProvinces(Provinces provinces) {
		this.provinces = provinces;
	}

	public List<Districts> getDistricts() {
		return districts;
	}

	public void setDistricts(List<Districts> districts) {
		this.districts = districts;
	}
	
	
}
