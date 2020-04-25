package com.formacionbdi.springboot.app.zipcodes.webservicezipcodesgendra.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "zipcodes")
public class Zipcode implements Serializable {
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="d_codigo")
	private String zCode;
	
	@Column(name="d_asenta")
	private String d_asenta;
	
	@Column(name="d_tipo_asenta")
	private String d_tipo_asenta;
	
	@Column(name="d_mnpio")
	private String d_mnpio;
	
	@Column(name="d_estado")
	private String d_estado;
	
	@Column(name="d_ciudad")
	private String d_ciudad;
	
	@Column(name="d_CP")
	private String d_CP;
	
	@Column(name="c_estado")
	private String c_estado;
	
	@Column(name="c_oficina")
	private String c_oficina;
	
	@Column(name="c_tipo_asenta")
	private String c_tipo_asenta;
	
	@Column(name="c_mnpio")
	private String c_mnpio;
	
	@Column(name="id_asenta_cpcons")
	private String id_asenta_cpcons;
	
	@Column(name="d_zona")
	private String d_zona;
	
	@Column(name="c_cve_ciudad")
	private String c_cve_ciudad;
	
	public Zipcode() {
		
	}
	
	public Zipcode(Long id, String zCode, String d_asenta, String d_tipo_asenta, String d_mnpio, String d_estado,
			String d_ciudad, String d_CP, String c_estado, String c_oficina, String c_tipo_asenta, String c_mnpio,
			String id_asenta_cpcons, String d_zona, String c_cve_ciudad) {
		this.id = id;
		this.zCode = zCode;
		this.d_asenta = d_asenta;
		this.d_tipo_asenta = d_tipo_asenta;
		this.d_mnpio = d_mnpio;
		this.d_estado = d_estado;
		this.d_ciudad = d_ciudad;
		this.d_CP = d_CP;
		this.c_estado = c_estado;
		this.c_oficina = c_oficina;
		this.c_tipo_asenta = c_tipo_asenta;
		this.c_mnpio = c_mnpio;
		this.id_asenta_cpcons = id_asenta_cpcons;
		this.d_zona = d_zona;
		this.c_cve_ciudad = c_cve_ciudad;
	}





	public Long getId() {
		return id;
	}





	public String getzCode() {
		return zCode;
	}





	public void setzCode(String zCode) {
		this.zCode = zCode;
	}





	public String getD_asenta() {
		return d_asenta;
	}





	public void setD_asenta(String d_asenta) {
		this.d_asenta = d_asenta;
	}





	public String getD_tipo_asenta() {
		return d_tipo_asenta;
	}





	public void setD_tipo_asenta(String d_tipo_asenta) {
		this.d_tipo_asenta = d_tipo_asenta;
	}





	public String getD_mnpio() {
		return d_mnpio;
	}





	public void setD_mnpio(String d_mnpio) {
		this.d_mnpio = d_mnpio;
	}





	public String getD_estado() {
		return d_estado;
	}





	public void setD_estado(String d_estado) {
		this.d_estado = d_estado;
	}





	public String getD_ciudad() {
		return d_ciudad;
	}





	public void setD_ciudad(String d_ciudad) {
		this.d_ciudad = d_ciudad;
	}





	public String getD_CP() {
		return d_CP;
	}





	public void setD_CP(String d_CP) {
		this.d_CP = d_CP;
	}





	public String getC_estado() {
		return c_estado;
	}





	public void setC_estado(String c_estado) {
		this.c_estado = c_estado;
	}





	public String getC_oficina() {
		return c_oficina;
	}





	public void setC_oficina(String c_oficina) {
		this.c_oficina = c_oficina;
	}





	public String getC_tipo_asenta() {
		return c_tipo_asenta;
	}





	public void setC_tipo_asenta(String c_tipo_asenta) {
		this.c_tipo_asenta = c_tipo_asenta;
	}





	public String getC_mnpio() {
		return c_mnpio;
	}





	public void setC_mnpio(String c_mnpio) {
		this.c_mnpio = c_mnpio;
	}





	public String getId_asenta_cpcons() {
		return id_asenta_cpcons;
	}





	public void setId_asenta_cpcons(String id_asenta_cpcons) {
		this.id_asenta_cpcons = id_asenta_cpcons;
	}





	public String getD_zona() {
		return d_zona;
	}





	public void setD_zona(String d_zona) {
		this.d_zona = d_zona;
	}





	public String getC_cve_ciudad() {
		return c_cve_ciudad;
	}





	public void setC_cve_ciudad(String c_cve_ciudad) {
		this.c_cve_ciudad = c_cve_ciudad;
	}





	private static final long serialVersionUID = 4092262034111501833L;
	
	
}
