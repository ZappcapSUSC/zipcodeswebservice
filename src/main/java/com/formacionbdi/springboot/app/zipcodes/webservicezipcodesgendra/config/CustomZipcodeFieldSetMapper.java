package com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.config;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.entity.Zipcode;

public class CustomZipcodeFieldSetMapper implements FieldSetMapper<Zipcode>{

	@Override
	public Zipcode mapFieldSet(FieldSet fieldSet) throws BindException {
		Zipcode zipcode = new Zipcode();

		zipcode.setzCode(fieldSet.readString(0));
		zipcode.setD_asenta(fieldSet.readString(1));
		zipcode.setD_tipo_asenta(fieldSet.readString(2));
		zipcode.setD_mnpio(fieldSet.readString(3));
		zipcode.setD_estado(fieldSet.readString(4));
		zipcode.setD_ciudad(fieldSet.readString(5));
		zipcode.setD_CP(fieldSet.readString(6));
		zipcode.setC_estado(fieldSet.readString(7));
		zipcode.setC_oficina(fieldSet.readString(8));
		zipcode.setC_tipo_asenta(fieldSet.readString(10));
		zipcode.setC_mnpio(fieldSet.readString(11));
		zipcode.setId_asenta_cpcons(fieldSet.readString(12));
		zipcode.setD_zona(fieldSet.readString(13));
		zipcode.setC_cve_ciudad(fieldSet.readString(14));
		
		return zipcode;
	}

}
