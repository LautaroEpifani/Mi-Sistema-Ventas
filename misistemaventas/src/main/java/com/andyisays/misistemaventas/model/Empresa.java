package com.andyisays.misistemaventas.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;




@Entity
@Table(name = "EMPRESAS")
public class Empresa  {
	
	
	

 	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;
 	
 	
 	
    //@NotNull(message = "Debes especificar el nombre")
    @Size(min = 1, max = 50, message = "El nombre debe medir entre 1 y 50")
 	@Column(name="NOMBRE")
	private String nombre;
 	
 	
    //@NotNull(message = "Debes especificar el sector")
    @Size(min = 1, max = 50, message = "El sector debe medir entre 1 y 50")
 	@Column(name="SECTOR")
 	private String sector;
 	
    
    //@NotNull(message = "Debes especificar la dirección")
    @Size(min = 1, max = 50, message = "La dirección debe medir entre 1 y 50")
 	@Column(name="DIRECCION")
 	private String direccion;
 	
    //@NotNull(message = "Debes especificar el teléfono")
    @Size(min = 1, max = 50, message = "El teléfono debe medir entre 1 y 50")
 	@Column(name="TELEFONO")
 	private String telefono;
 	
 	
 	


	



	public Long getId() {
	return id;
}



public void setId(Long id) {
	this.id = id;
}



	public String getNombre() {
		return nombre;
	}



	public void setNombre(String nombre) {
		this.nombre = nombre;
	}



	



	public String getSector() {
		return sector;
	}



	public void setSector(String sector) {
		this.sector = sector;
	}



	public String getDireccion() {
		return direccion;
	}



	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}



	public String getTelefono() {
		return telefono;
	}



	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	
  
 	 
 	 
 	 
 	 
 	 
 	 
 	
	
	

}
