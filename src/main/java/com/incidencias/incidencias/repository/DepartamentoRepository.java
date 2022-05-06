package com.incidencias.incidencias.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.incidencias.incidencias.entity.Departamento;

@Repository("DepartamentoRepository")
public interface DepartamentoRepository extends CrudRepository<Departamento, Integer> {
}
