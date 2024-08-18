package com.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.model.Provinces;

@Repository
public interface ProvincesRepository extends JpaRepository<Provinces, Integer>{

}
