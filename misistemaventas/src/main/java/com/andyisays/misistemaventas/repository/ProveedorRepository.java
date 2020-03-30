package com.andyisays.misistemaventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.andyisays.misistemaventas.model.Proveedor;



	
	public interface ProveedorRepository extends JpaRepository<Proveedor, Long>, 
	PagingAndSortingRepository<Proveedor,Long> {
	
	
	//@Query("SELECT p FROM Persona p")
	//Iterable<Persona> find();

	


}
