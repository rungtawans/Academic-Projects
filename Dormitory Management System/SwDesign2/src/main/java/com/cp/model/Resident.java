package com.cp.model;

import java.util.List;

import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "resident")
public class Resident {

	@Id
	// @UuidGenerator
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_card")
	private String id_card;

	@Column(name = "first_name")
	private String first_name;

	@Column(name = "last_name")
	private String last_name;

	@Column(name = "email")
	private String email;

	@Column(name = "phone")
	private String phone;

	@Column(name = "address")
	private String address;

	@JsonIgnore
	@ManyToOne
//	(optional=false)
	@JoinColumn(name = "districts_id")
	private Districts districts;

	@JsonIgnore
	@OneToMany(targetEntity = Lease.class, mappedBy = "resident", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Lease> lease;

	public Resident() {
		super();
	}

	public Resident(String id_card, String first_name, String last_name, String email, String phone, String address,
			Districts districts) {
		super();
		this.id_card = id_card;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.districts = districts;
	}

	public Resident(String id_card, String first_name, String last_name, String email, String phone, String address,
			Districts districts, List<Lease> lease) {
		super();
		this.id_card = id_card;
		this.first_name = first_name;
		this.last_name = last_name;
		this.email = email;
		this.phone = phone;
		this.address = address;
		this.districts = districts;
		this.lease = lease;
	}

	public String getId_card() {
		return id_card;
	}

	public void setId_card(String id_card) {
		this.id_card = id_card;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Districts getDistricts() {
		return districts;
	}

	public void setDistricts(Districts districts) {
		this.districts = districts;
	}

	public List<Lease> getLease() {
		return lease;
	}

	public void setLease(List<Lease> lease) {
		this.lease = lease;
	}

	public Resident orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

}
