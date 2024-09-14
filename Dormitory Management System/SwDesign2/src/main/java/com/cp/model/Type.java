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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "type")
public class Type {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@UuidGenerator
	@Column(name = "type_id")
	private String type_id;

	@Column(name = "price")
	private String price;

	@Column(name = "type_desc")
	private String type_desc;

	@JsonIgnore
	@OneToMany(targetEntity = Room.class, mappedBy = "type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Room> room;

	public Type() {
		super();
	}

	public Type(String type_id, String price, String type_desc, List<Room> room) {
		super();
		this.type_id = type_id;
		this.price = price;
		this.type_desc = type_desc;
		this.room = room;
	}

	public String getType_id() {
		return type_id;
	}

	public void setType_id(String type_id) {
		this.type_id = type_id;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getType_desc() {
		return type_desc;
	}

	public void setType_desc(String type_desc) {
		this.type_desc = type_desc;
	}

	public List<Room> getRoom() {
		return room;
	}

	public void setRoom(List<Room> room) {
		this.room = room;
	}

}
