package com.application.rest.persistence;

import com.application.rest.entity.Fabricante;

import java.util.List;
import java.util.Optional;

public interface IFabricanteDAO {

    List<Fabricante> findAll();
    Optional<Fabricante> findById(Long id);
    void save(Fabricante fabricante);
    void deleteById(Long id);

}
