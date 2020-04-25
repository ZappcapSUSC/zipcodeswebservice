package com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.controllers;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Zipcode Not Found")
public class ZipcodeNotFoundException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1982840859780655513L;
	
}
