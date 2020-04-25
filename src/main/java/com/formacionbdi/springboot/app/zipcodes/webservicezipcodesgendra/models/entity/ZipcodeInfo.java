package com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.entity;

import java.util.ArrayList;
import java.util.List;

public class ZipcodeInfo {
	
	/*Class to map the response for the client*/
	
	private String zip_code;
	private String locality;
	private String federal_entity;
	private List<Settlement> settlements;
	private String municipality;
	
	public ZipcodeInfo() {
		
	}
	
	public ZipcodeInfo(String zip_code, String locality, String federal_entity, List<Settlement> settlements,
			String municipality) {
		this.zip_code = zip_code;
		this.locality = locality;
		this.federal_entity = federal_entity;
		this.settlements = settlements;
		this.municipality = municipality;
	}
	
	public ZipcodeInfo(List<Zipcode> zipcodes) {
		this.zip_code = zipcodes.get(0).getzCode().toString();
		this.locality = zipcodes.get(0).getD_mnpio();
		this.federal_entity = zipcodes.get(0).getD_estado();
		this.municipality = zipcodes.get(0).getD_ciudad();
		this.settlements = new ArrayList<Settlement>();
		
		for(Zipcode z : zipcodes) {
			Settlement settlement = new Settlement(z.getD_asenta(), z.getD_zona(), z.getD_tipo_asenta());
			this.settlements.add(settlement);
		}
	}
	
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getLocality() {
		return locality;
	}
	public void setLocality(String locality) {
		this.locality = locality;
	}
	public String getFederal_entity() {
		return federal_entity;
	}
	public void setFederal_entity(String federal_entity) {
		this.federal_entity = federal_entity;
	}
	public List<Settlement> getSettlements() {
		return settlements;
	}
	public void setSettlements(List<Settlement> settlements) {
		this.settlements = settlements;
	}
	public String getMunicipality() {
		return municipality;
	}
	public void setMunicipality(String municipality) {
		this.municipality = municipality;
	}
	

}

class Settlement {
	private String name;
	private String zone_type;
	private String settlement_type;
	
	public Settlement(String name, String zone_type, String settlement_type) {
		this.name = name;
		this.zone_type = zone_type;
		this.settlement_type = settlement_type;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getZone_type() {
		return zone_type;
	}
	public void setZone_type(String zone_type) {
		this.zone_type = zone_type;
	}
	public String getSettlement_type() {
		return settlement_type;
	}
	public void setSettlement_type(String settlement_type) {
		this.settlement_type = settlement_type;
	}
	
}

