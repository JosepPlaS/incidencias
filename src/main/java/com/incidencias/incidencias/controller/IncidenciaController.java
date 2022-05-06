package com.incidencias.incidencias.controller;

import com.incidencias.incidencias.entity.Incidencia;
import com.incidencias.incidencias.repository.IncidenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/incidencia")
public class IncidenciaController {

    @Autowired
    @Qualifier("IncidenciaRepository")
    private IncidenciaRepository repository;

    @PostMapping("/insert")
    public Incidencia insert(@RequestBody Incidencia incidencia) {
        try {
            repository.save(incidencia);
            return incidencia;
        } catch (Exception ex) {
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public Incidencia update(@RequestBody Incidencia incidencia, @PathVariable Integer id) {
        try {
            Incidencia inc = repository.findById(id).get();
            inc.setTipo_incidencia(incidencia.getTipo_incidencia());
            inc.setFecha_incidencia(incidencia.getFecha_incidencia());
            inc.setFecha_introduccion(incidencia.getFecha_introduccion());
            inc.setAula(incidencia.getAula());
            inc.setDescripcion(incidencia.getDescripcion());
            inc.setObservaciones(incidencia.getObservaciones());
            inc.setFecha_finalizacion(incidencia.getFecha_finalizacion());
            inc.setTiempo_invertido(incidencia.getTiempo_invertido());

            // RELACIONES CONDICIONALES
            if (incidencia.getResponsable() != null)
                inc.setReportador(incidencia.getResponsable());
            if (incidencia.getIncidencia_sw() != null)
                inc.setIncidencia_sw(incidencia.getIncidencia_sw());
            if (incidencia.getIncidencia_hw() != null)
                inc.setIncidencia_hw(incidencia.getIncidencia_hw());

            repository.save(inc);
            return inc;
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/all")
    public Iterable<Incidencia> getAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public Incidencia getOne(@PathVariable Integer id) {
        try {
            return repository.findById(id).get();
        } catch (Exception ex) {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public Incidencia delete(@PathVariable Integer id) {
        try {
            Incidencia incidencia = repository.findById(id).get();
            repository.delete(incidencia);
            return incidencia;
        } catch (Exception ex) {
            return null;
        }
    }
}
