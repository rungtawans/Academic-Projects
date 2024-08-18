package com.cp.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cp.model.Unit;


@Repository
public interface UnitRepository extends CrudRepository<Unit,Integer>{

}
