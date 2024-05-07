package com.application.rest.persistence.impl;

import com.application.rest.entity.Producto;
import com.application.rest.persistence.IProductoDAO;
import com.application.rest.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Component
public class ProductoDAOImpl implements IProductoDAO {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public List<Producto> findAll() {
        return (List<Producto>) productoRepository.findAll();
    }

    @Override
    public Optional<Producto> findById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public List<Producto> findByPriceInRange(BigDecimal minPrice, BigDecimal maxPrice) {
        return productoRepository.findProductoByPrecioBetween(minPrice, maxPrice);
    }
    @Override
    public void save(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void deleteById(Long id) {
        productoRepository.deleteById(id);
    }
}
