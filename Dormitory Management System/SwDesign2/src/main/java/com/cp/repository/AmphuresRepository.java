package com.cp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.model.Amphures;

@Repository
public interface AmphuresRepository extends JpaRepository<Amphures, Integer>{

}
