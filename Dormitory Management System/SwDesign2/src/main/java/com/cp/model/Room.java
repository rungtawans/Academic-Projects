package com.cp.model;

	import java.util.List;


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
@Table(name="room")
public class Room {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="room_id")
 	private String room_id;
	
	@Column(name="room_status")
	private String room_status;

	@Column(name="room_desc")
	private String room_desc;
	
	@JsonIgnore
	@ManyToOne
//	(optional=false)
	@JoinColumn(name="type_id")
	private Type type;
	
	@JsonIgnore
	@OneToMany(targetEntity=Lease.class, mappedBy="room",
    		cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Lease> lease;

	
	public Room() {
		super();
	}
	public Room(String room_id, String room_desc, String room_status, Type type, List<Lease> lease) {
		super();
		this.room_id = room_id;
		this.room_status = room_status;
		this.room_desc = room_desc;
		this.type = type;
		this.lease = lease;
	}

	public String getRoom_id() {
		return room_id;
	}

	public void setRoom_id(String room_id) {
		this.room_id = room_id;
	}

	public String getRoom_status() {
		return room_status;
	}

	public void setRoom_status(String room_status) {
		this.room_status = room_status;
	}

	public String getRoom_desc() {
		return room_desc;
	}

	public void setRoom_desc(String room_desc) {
		this.room_desc = room_desc;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public List<Lease> getLease() {
		return lease;
	}

	public void setLease(List<Lease> lease) {
		this.lease = lease;
	}
	
	public Room orElse(Object object) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
}
