package com.application.rest.repository;

import com.application.rest.entity.Fabricante;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteRepository  extends CrudRepository<Fabricante, Long> {
}
