package com.incidencias.incidencias.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.incidencias.incidencias.entity.TipoHardware;

@Repository("TipoHardwareRepository")
public interface TipoHardwareRepository extends CrudRepository<TipoHardware, Integer> {
}
