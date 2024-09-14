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
@Table(name = "districts")
public class Districts {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private Integer zip_code;
	private String name_th;
	private String name_en;

	@JsonIgnore
	@ManyToOne(optional = false)
	@JoinColumn(name = "amphure_id")
	private Amphures amphures;

	// link to Resident
	@JsonIgnore
	@OneToMany(targetEntity = Resident.class, mappedBy = "districts", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Resident> residents;

	public Districts() {
		super();
	}

	public Districts(Integer id, Integer zip_code, String name_th, String name_en, Amphures amphures,
			List<Resident> residents) {
		super();
		this.id = id;
		this.zip_code = zip_code;
		this.name_th = name_th;
		this.name_en = name_en;
		this.amphures = amphures;
		this.residents = residents;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getZip_code() {
		return zip_code;
	}

	public void setZip_code(Integer zip_code) {
		this.zip_code = zip_code;
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

	public Amphures getAmphures() {
		return amphures;
	}

	public void setAmphures(Amphures amphures) {
		this.amphures = amphures;
	}

	public List<Resident> getResidents() {
		return residents;
	}

	public void setResidents(List<Resident> residents) {
		this.residents = residents;
	}

}
