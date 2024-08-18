package com.cp.model;

import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
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
@Table(name="lease")
public class Lease {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="lease_id")
     private Integer lease_id;

	@JsonIgnore
    @OneToMany(targetEntity=Bill.class, mappedBy="lease",
            cascade=CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Bill> bill;

    @JsonIgnore 
    @ManyToOne
    (optional=false)
    @JoinColumn(name="id_card")
    private Resident resident;

    @JsonIgnore 
    @ManyToOne
    (optional=false)
    @JoinColumn(name="room_id")
    private Room room;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="start_date_lease")
    private Date start_date_lease;
    
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name="end_date_lease")
    private Date end_date_lease;

    @Column(name="deposit")
     private Integer deposit;

    @Column(name="discount")
     private Integer discount;

    @Column(name="pet")
     private String pet;

    @Column(name="lease_status")
     private String lease_status;

    @Column(name="member")
     private Integer member;

    
	public Lease() {
		super();
	}

	
	public Lease(Resident resident, Room room, Date start_date_lease, Date end_date_lease, Integer deposit,
			Integer discount, String pet, Integer member, String lease_status) {
		super();
		this.resident = resident;
		this.room = room;
		this.start_date_lease = start_date_lease;
		this.end_date_lease = end_date_lease;
		this.deposit = deposit;
		this.discount = discount;
		this.pet = pet;
		this.member = member;
		this.lease_status = lease_status;
	}

	public Lease(Integer lease_id, List<Bill> bill, Resident resident, Room room, Date start_date_lease,
			Date end_date_lease, Integer deposit, Integer discount, String pet, String lease_status, Integer member) {
		super();
		this.lease_id = lease_id;
		this.bill = bill;
		this.resident = resident;
		this.room = room;
		this.start_date_lease = start_date_lease;
		this.end_date_lease = end_date_lease;
		this.deposit = deposit;
		this.discount = discount;
		this.pet = pet;
		this.lease_status = lease_status;
		this.member = member;
	}

	public Integer getLease_id() {
		return lease_id;
	}

	public void setLease_id(Integer lease_id) {
		this.lease_id = lease_id;
	}

	public List<Bill> getBill() {
		return bill;
	}

	public void setBill(List<Bill> bill) {
		this.bill = bill;
	}

	public Resident getResident() {
		return resident;
	}

	public void setResident(Resident resident) {
		this.resident = resident;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public Date getStart_date_lease() {
		return start_date_lease;
	}

	public void setStart_date_lease(Date start_date_lease) {
		this.start_date_lease = start_date_lease;
	}

	public Date getEnd_date_lease() {
		return end_date_lease;
	}

	public void setEnd_date_lease(Date end_date_lease) {
		this.end_date_lease = end_date_lease;
	}

	public Integer getDeposit() {
		return deposit;
	}

	public void setDeposit(Integer deposit) {
		this.deposit = deposit;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getPet() {
		return pet;
	}

	public void setPet(String pet) {
		this.pet = pet;
	}

	public String getLease_status() {
		return lease_status;
	}

	public void setLease_status(String lease_status) {
		this.lease_status = lease_status;
	}

	public Integer getMember() {
		return member;
	}

	public void setMember(Integer member) {
		this.member = member;
	}

	
}
