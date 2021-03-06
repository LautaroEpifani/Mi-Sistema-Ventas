package com.andyisays.misistemaventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.andyisays.misistemaventas.model.Proveedor;

public interface ProveedoresRepository extends JpaRepository<Proveedor, Long> {
	
	Proveedor findFirstByNombre(String nombre);

	Proveedor findByNombre(String nombre);


}
