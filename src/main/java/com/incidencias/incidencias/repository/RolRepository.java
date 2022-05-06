package com.incidencias.incidencias.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.incidencias.incidencias.entity.Rol;

@Repository("RolRepository")
public interface RolRepository extends CrudRepository<Rol, Integer> {
}
