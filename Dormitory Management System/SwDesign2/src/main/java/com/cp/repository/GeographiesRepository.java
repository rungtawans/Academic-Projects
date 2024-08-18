package com.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.model.Geographies;

@Repository
public interface GeographiesRepository extends JpaRepository<Geographies, Integer>{

}
