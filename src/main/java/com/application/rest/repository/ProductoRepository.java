package com.application.rest.repository;

import com.application.rest.entity.Producto;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Long> {

    //  Los 2 metodos hacen lo mismo la difencia es que una utilaza las consultas mediante queris y la otra directamente
    /*@Query("SELECT p FROM Producto p WHERE p.precio BETWEEN ?1 AND ?2")
    List<Producto> findProductoByPrecioInRange(BigDecimal mixPrice, BigDecimal maxPrice);
    
     */
    List<Producto> findProductoByPrecioBetween(BigDecimal mixPrice, BigDecimal maxPrice);
}
