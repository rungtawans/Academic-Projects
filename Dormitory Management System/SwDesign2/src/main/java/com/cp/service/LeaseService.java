package com.cp.service;

import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import com.cp.model.Bill;
import com.cp.model.Lease;
import com.cp.repository.LeaseRepository;

@Service
public class LeaseService {

	private LeaseRepository leaseRepository;

	// constructor dependency injection
	public LeaseService(LeaseRepository leaseRepository) {
		super();
		this.leaseRepository = leaseRepository;
	}

	public List<Lease> getAllLease() {
		return (List<Lease>) this.leaseRepository.findAll();
	}

	public Lease getLeaseById(Integer id) {
		return this.leaseRepository.findById(id).get();
	}

	public void deleteLeaseById(Integer id) {
		Lease lease = this.leaseRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid Lease Id:" + id));
		leaseRepository.delete(lease);
	}

	public void saveLeaseById(Lease l) {
		this.leaseRepository.save(l);
	}

	public void saveLease(Lease l) {
		this.leaseRepository.save(l);
	}

	public Bill findLatestBill(Lease lease) {
		if (lease != null) {
			List<Bill> bills = lease.getBill();
			if (!bills.isEmpty()) {
				// Use Java Stream to find the Bill with the latest pay_date
				return bills.stream().max(Comparator.comparing(bill -> bill.getMonth().getPay_date())).orElse(null);
			}
		}
		return null; // Or handle the case when the lease or bills don't exist
	}

}
