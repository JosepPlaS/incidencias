package com.incidencias.incidencias.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.incidencias.incidencias.entity.Incidencia;

@Repository("IncidenciaRepository")
public interface IncidenciaRepository extends CrudRepository<Incidencia, Integer> {
}
