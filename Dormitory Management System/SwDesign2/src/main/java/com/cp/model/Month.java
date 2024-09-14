package com.cp.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.Date;

@Entity
@Table(name = "month")
public class Month {
	@Id
	@Column(name = "pay_date")
	private Date pay_date;
	@Column(name = "last_pay_date")
	private Date last_pay_date;
	@JsonIgnore
	@OneToMany(targetEntity = Bill.class, mappedBy = "month", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Bill> bills;

	public Month() {
		super();
	}

	public Month(Date pay_date, Date last_pay_date, List<Bill> bills) {
		super();
		this.pay_date = pay_date;
		this.last_pay_date = last_pay_date;
		this.bills = bills;
	}

	public Date getPay_date() {
		return pay_date;
	}

	public void setPay_date(Date pay_date) {
		this.pay_date = pay_date;
	}

	public Date getLast_pay_date() {
		return last_pay_date;
	}

	public void setLast_pay_date(Date last_pay_date) {
		this.last_pay_date = last_pay_date;
	}

	public List<Bill> getBills() {
		return bills;
	}

	public void setBills(List<Bill> bills) {
		this.bills = bills;
	}

}
