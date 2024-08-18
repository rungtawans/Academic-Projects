package com.cp.repository;


import java.util.Date;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cp.model.Month;
@Repository
public interface MonthRepository extends CrudRepository<Month,Date>{

}
