package com.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.model.Resident;

@Repository
public interface ResidentRepository extends JpaRepository<Resident, String> {

}
