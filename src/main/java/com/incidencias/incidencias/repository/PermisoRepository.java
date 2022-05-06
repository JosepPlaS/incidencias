package com.incidencias.incidencias.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.incidencias.incidencias.entity.Permiso;

@Repository("PermisoRepository")
public interface PermisoRepository extends CrudRepository<Permiso, Integer> {
}
