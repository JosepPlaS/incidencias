package com.incidencias.incidencias.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.incidencias.incidencias.entity.Profesor;

@Repository("ProfesorRepository")
public interface ProfesorRepository extends CrudRepository<Profesor, Integer> {
}
