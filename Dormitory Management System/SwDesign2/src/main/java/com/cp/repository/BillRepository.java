package com.cp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cp.model.Bill;

@Repository
public interface BillRepository extends CrudRepository<Bill,Integer> {

}
