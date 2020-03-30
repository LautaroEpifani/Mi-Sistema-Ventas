package com.andyisays.misistemaventas.model;

import java.io.Serializable;
import java.util.List;

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

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;




@Entity
@Table(name = "PROVEEDORES")
public class Proveedor  {
	
	
	

 	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "ID")
    private Long id;
 	
 	
 	
 	@Column(name="NOMBRE")
	private String nombre;
 	
 	
 	
 	
 	@ManyToMany
	@JoinTable(name = "tb_fk_producto_proveedor",  
    joinColumns = @JoinColumn(name="proveedor_id", referencedColumnName="ID", nullable=false), 
    inverseJoinColumns = @JoinColumn(name = "producto_id", referencedColumnName="ID", nullable=false))
 	private List<Producto> productos;



	



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



	public List<Producto> getProductos() {
		return productos;
	}



	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}



	
  
 	 
 	 
 	 
 	 
 	 
 	 
 	
	
	

}
