package com.incidencias.incidencias.controller;

import java.util.ArrayList;

import com.incidencias.incidencias.dto.IncidenciaDTO;
import com.incidencias.incidencias.entity.Incidencia;
import com.incidencias.incidencias.repository.EstadoRepository;
import com.incidencias.incidencias.repository.IncidenciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    @Qualifier("EstadoRepository")
    private EstadoRepository estadoRepository;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Incidencia incidencia) {
        try {
            repository.save(incidencia);
            return new ResponseEntity<Incidencia>(repository.findById(incidencia.getId()).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Incidencia incidencia, @PathVariable Integer id) {
        try {
            Incidencia inc = repository.findById(id).get();

            if (incidencia.getTipo_incidencia() != null)
                inc.setTipo_incidencia(incidencia.getTipo_incidencia());
            if (incidencia.getFecha_incidencia() != null)
                inc.setFecha_incidencia(incidencia.getFecha_incidencia());
            if (incidencia.getFecha_introduccion() != null)
                inc.setFecha_introduccion(incidencia.getFecha_introduccion());
            if (incidencia.getAula() != null)
                inc.setAula(incidencia.getAula());
            if (incidencia.getDescripcion() != null)
                inc.setDescripcion(incidencia.getDescripcion());
            if (incidencia.getObservaciones() != null)
                inc.setObservaciones(incidencia.getObservaciones());
            if (incidencia.getFecha_finalizacion() != null)
                inc.setFecha_finalizacion(incidencia.getFecha_finalizacion());
            if (incidencia.getTiempo_invertido() != null)
                inc.setTiempo_invertido(incidencia.getTiempo_invertido());
            inc.setHistorial(incidencia.getHistorial());

            // RELACIONES CONDICIONALES
            if (incidencia.getResponsable() != null)
                inc.setResponsable(incidencia.getResponsable());
            if (incidencia.getReportador() != null)
                inc.setReportador(incidencia.getReportador());

            inc.setIncidencia_sw(incidencia.getIncidencia_sw());
            inc.setIncidencia_hw(incidencia.getIncidencia_hw());

            repository.save(inc);
            return new ResponseEntity<Incidencia>(inc, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/comunicar/{id}")
    public ResponseEntity<?> updateResponsable(@RequestBody Incidencia incidencia, @PathVariable Integer id) {
        try {
            Incidencia inc = repository.findById(id).get();

            if (incidencia.getResponsable() != null) {

                inc.setHistorial(incidencia.getHistorial());

                inc.setEstado(estadoRepository.findByCodigo("Comunicada").get(0));

                // ENVIAR CORREO

                inc.setResponsable(incidencia.getResponsable());

            } else {
                inc.setHistorial(incidencia.getHistorial());
                inc.setResponsable(null);
                inc.setEstado(estadoRepository.findByCodigo("En proceso").get(0));

                // ENVIAR CORREO
            }
            repository.save(inc);
            return new ResponseEntity<Incidencia>(inc, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/trabajar/{id}")
    public ResponseEntity<?> workingOn(@RequestBody Incidencia incidencia, @PathVariable Integer id) {
        try {
            Incidencia inc = repository.findById(id).get();

            inc.setHistorial(incidencia.getHistorial());

            inc.setEstado(estadoRepository.findByCodigo("En proceso").get(0));

            // ENVIAR CORREO

            repository.save(inc);
            return new ResponseEntity<Incidencia>(inc, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/viable/{id}")
    public ResponseEntity<?> solved(@RequestBody Incidencia incidencia, @PathVariable Integer id) {
        try {
            Incidencia inc = repository.findById(id).get();

            inc.setHistorial(incidencia.getHistorial());
            inc.setTiempo_invertido(incidencia.getTiempo_invertido());
            inc.setFecha_finalizacion(incidencia.getFecha_finalizacion());
            inc.setObservaciones(incidencia.getObservaciones());

            inc.setEstado(estadoRepository.findByCodigo("Solucionada").get(0));

            // ENVIAR CORREO

            repository.save(inc);
            return new ResponseEntity<Incidencia>(inc, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/inviable/{id}")
    public ResponseEntity<?> unsolved(@RequestBody Incidencia incidencia, @PathVariable Integer id) {
        try {
            Incidencia inc = repository.findById(id).get();

            inc.setHistorial(incidencia.getHistorial());
            inc.setTiempo_invertido(incidencia.getTiempo_invertido());
            inc.setFecha_finalizacion(incidencia.getFecha_finalizacion());
            inc.setObservaciones(incidencia.getObservaciones());

            inc.setEstado(estadoRepository.findByCodigo("Solución inviable").get(0));

            // ENVIAR CORREO

            repository.save(inc);
            return new ResponseEntity<Incidencia>(inc, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            ArrayList<IncidenciaDTO> incidencias = new ArrayList<IncidenciaDTO>();

            for (Incidencia inc : repository.findAll())
                incidencias.add(new IncidenciaDTO(inc));

            return new ResponseEntity<Iterable<IncidenciaDTO>>(incidencias, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Incidencia>(repository.findById(id).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Incidencia incidencia = repository.findById(id).get();
            repository.delete(incidencia);
            return new ResponseEntity<Incidencia>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
