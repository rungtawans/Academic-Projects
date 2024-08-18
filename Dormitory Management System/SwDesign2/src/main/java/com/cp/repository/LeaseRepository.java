package com.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.model.Lease ;

@Repository
public interface LeaseRepository extends JpaRepository<Lease,Integer>{

}
