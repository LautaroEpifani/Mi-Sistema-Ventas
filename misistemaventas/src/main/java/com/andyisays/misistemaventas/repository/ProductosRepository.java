package com.andyisays.misistemaventas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.andyisays.misistemaventas.model.Producto;


public interface ProductosRepository extends CrudRepository<Producto, Integer> {

    Producto findFirstByCodigo(String codigo);
    
    
    @Query(value= "select * from productos where nombre like %?%", nativeQuery=true)
	List<Producto> findRegistroPorPalabra(String palabraClave);
    
    
    //El Problema es que hace una consulta por cada proveedor.
    @Query(value= "select * from producto left join proveedores on producto.id=proveedores.id", nativeQuery=true)
	List<Producto> findAllProdProv();
    
    //Con el Distinct resuelve el problemas de las consultas duplicadas en el front PERO Hibernate sigue haciendo las consultas.
    @Query("select distinct(p) from Producto p left join p.proveedores")
    List<Producto> findAllProductoProveedor();
    
    
    //El puto fetch resuelve los problemas de los multiples joins
    @Query("select distinct(p) from Producto p left join fetch p.proveedores")
    List<Producto> findAllProduProveedor();
    
   
}
