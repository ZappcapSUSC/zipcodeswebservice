package com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.entity.Zipcode;

public interface ZipcodeRepository extends CrudRepository<Zipcode, Long>{
	
	List<Zipcode> findByzCode(String zCode);
	
}
