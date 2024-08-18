package com.cp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cp.model.Type;

@Repository
public interface TypeRepository extends JpaRepository<Type, String> {

}
