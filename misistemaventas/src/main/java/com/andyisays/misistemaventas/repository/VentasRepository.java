package com.andyisays.misistemaventas.repository;

import org.springframework.data.repository.CrudRepository;

import com.andyisays.misistemaventas.model.Venta;


public interface VentasRepository extends CrudRepository<Venta, Integer> {
}
