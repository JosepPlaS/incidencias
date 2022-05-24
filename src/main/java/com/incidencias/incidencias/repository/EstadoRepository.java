package com.incidencias.incidencias.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.incidencias.incidencias.entity.Estado;

@Repository("EstadoRepository")
public interface EstadoRepository extends CrudRepository<Estado, Integer> {
    List<Estado> findByCodigo(String codigo);
}
