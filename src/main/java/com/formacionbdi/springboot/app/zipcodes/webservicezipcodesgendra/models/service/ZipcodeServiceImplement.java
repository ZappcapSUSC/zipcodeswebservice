package com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.controllers.ZipcodeNotFoundException;
import com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.entity.Zipcode;
import com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.entity.ZipcodeInfo;
import com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.repository.ZipcodeRepository;

@Service
public class ZipcodeServiceImplement implements IZipcodeService{

	@Autowired
	private ZipcodeRepository zipcodeRepository;
	
	@Override
	@Transactional(readOnly = true)
	public List<Zipcode> findAll() {
		return (List<Zipcode>)zipcodeRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ZipcodeInfo findByZipcode(String z_code) throws ZipcodeNotFoundException {
		List<Zipcode> response = zipcodeRepository.findByzCode(z_code);
		if(response.isEmpty()) {
			throw new ZipcodeNotFoundException();
		}
		return new ZipcodeInfo(response);
	}

}
