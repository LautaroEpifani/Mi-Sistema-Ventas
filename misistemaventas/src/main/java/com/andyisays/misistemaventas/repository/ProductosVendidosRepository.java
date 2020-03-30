package com.andyisays.misistemaventas.repository;

import org.springframework.data.repository.CrudRepository;

import com.andyisays.misistemaventas.model.ProductoVendido;


public interface ProductosVendidosRepository extends CrudRepository<ProductoVendido, Integer> {

}
