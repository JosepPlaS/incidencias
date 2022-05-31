package com.incidencias.incidencias.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

import com.incidencias.incidencias.entity.Incidencia;
import com.incidencias.incidencias.entity.Profesor;

@Repository("IncidenciaRepository")
public interface IncidenciaRepository extends CrudRepository<Incidencia, Integer> {
    List<Incidencia> findByReportador(Profesor reportador);

    List<Incidencia> findByResponsable(Profesor responsable);

    @Query(value = "select * from incidencia i where i.tipo_incidencia = :tipo_incidencia", nativeQuery = true)
    List<Incidencia> findByTipo(Integer tipo_incidencia);

    @Query(value = "select * from incidencia i where YEAR(i.fecha_introduccion) = :anyo", nativeQuery = true)
    List<Incidencia> findByAnyo(Integer anyo);
}
