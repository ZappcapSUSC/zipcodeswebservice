package com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.entity.Zipcode;
import com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.entity.ZipcodeInfo;
import com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.service.IZipcodeService;

@RestController
public class ZipcodeController {
	@Autowired
	private IZipcodeService zipcodeService;
	
	@GetMapping("/list_all")
	public List<Zipcode> listar(){
		return zipcodeService.findAll();
	}
	
	@GetMapping(value="/zip-codes/{z_code}")
	public ZipcodeInfo getZipcodeInfo(@PathVariable String z_code) {
		try {
			return zipcodeService.findByZipcode(z_code);
		} catch(ZipcodeNotFoundException ex) {
	        throw new ResponseStatusException(
	                HttpStatus.NOT_FOUND, "Zipcode not found", ex);
		}
		
	}
}
