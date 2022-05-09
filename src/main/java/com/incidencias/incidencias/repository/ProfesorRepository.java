package com.incidencias.incidencias.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.incidencias.incidencias.entity.Profesor;

@Repository("ProfesorRepository")
public interface ProfesorRepository extends CrudRepository<Profesor, Integer> {
    List<Profesor> findByEmail(String email);
}
