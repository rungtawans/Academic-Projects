package com.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.model.Districts;

@Repository
public interface DistrictsRepository extends JpaRepository<Districts, Integer>{

}
