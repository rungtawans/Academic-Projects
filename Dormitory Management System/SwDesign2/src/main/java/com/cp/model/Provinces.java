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
@Table(name="provinces")
public class Provinces {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	private String name_th;
	private String name_en;
	
	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "geography_id")
	private Geographies geographies;
	
	@JsonIgnore
	@OneToMany(targetEntity=Amphures.class, mappedBy="provinces",
    		cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Amphures> amphures;

	public Provinces() {
		super();
	}

	public Provinces(Integer id, String code, String name_th, String name_en, Geographies geographies,
			List<Amphures> amphures) {
		super();
		this.id = id;
		this.code = code;
		this.name_th = name_th;
		this.name_en = name_en;
		this.geographies = geographies;
		this.amphures = amphures;
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

	public Geographies getGeographies() {
		return geographies;
	}

	public void setGeographies(Geographies geographies) {
		this.geographies = geographies;
	}

	public List<Amphures> getAmphures() {
		return amphures;
	}

	public void setAmphures(List<Amphures> amphures) {
		this.amphures = amphures;
	}
	
	
}
