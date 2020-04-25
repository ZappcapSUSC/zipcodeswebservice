package com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.service;

import java.util.List;

import com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.controllers.ZipcodeNotFoundException;
import com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.entity.Zipcode;
import com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.entity.ZipcodeInfo;

public interface IZipcodeService {
	public List<Zipcode> findAll();
	public ZipcodeInfo findByZipcode(String zCode) throws ZipcodeNotFoundException;
}
