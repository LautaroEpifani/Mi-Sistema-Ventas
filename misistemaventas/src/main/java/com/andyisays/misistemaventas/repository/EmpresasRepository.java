package com.andyisays.misistemaventas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.andyisays.misistemaventas.model.Empresa;
import com.andyisays.misistemaventas.model.Proveedor;

public interface EmpresasRepository extends JpaRepository<Empresa, Long> {
	
	Empresa findFirstByNombre(String nombre);

	Empresa findByNombre(String nombre);


}
