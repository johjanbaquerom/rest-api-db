package com.application.rest.service;

import com.application.rest.entity.Producto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> findAll();
    Optional<Producto> findById(Long id);
    List<Producto> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice);
    void save(Producto producto);
    void deleteById(Long id);
}
