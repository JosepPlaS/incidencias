package com.incidencias.incidencias.controller;

import java.util.ArrayList;
import java.util.List;

import com.incidencias.incidencias.dto.EstadoDTO;
import com.incidencias.incidencias.dto.Mensaje;
import com.incidencias.incidencias.entity.Estado;
import com.incidencias.incidencias.repository.EstadoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
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
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    @Qualifier("EstadoRepository")
    private EstadoRepository repository;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Estado estado) {
        try {
            repository.save(estado);
            return new ResponseEntity<Estado>(repository.findById(estado.getId()).get(), HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<Mensaje>(new Mensaje("No puede crear el estado, codigo repetido."),
                    HttpStatus.valueOf(402));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Estado estado, @PathVariable Integer id) {
        try {
            Estado est = repository.findById(id).get();

            if (estado.getCodigo() != null)
                est.setCodigo(estado.getCodigo());
            if (estado.getDescripcion() != null)
                est.setDescripcion(estado.getDescripcion());

            repository.save(est);
            return new ResponseEntity<Estado>(est, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<Mensaje>(new Mensaje("No puede modificar el estado, codigo repetido."),
                    HttpStatus.valueOf(402));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            ArrayList<EstadoDTO> estados = new ArrayList<EstadoDTO>();

            for (Estado est : repository.findAll())
                estados.add(new EstadoDTO(est));

            return new ResponseEntity<Iterable<EstadoDTO>>(estados, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Estado>(repository.findById(id).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/c-{codigo}")
    public ResponseEntity<?> getByCodigo(@PathVariable String codigo) {
        try {
            List<Estado> estado = repository.findByCodigo(codigo);

            if (estado.size() > 1)
                return new ResponseEntity<Mensaje>(new Mensaje("Error, se han encontrado mas de un estado."),
                        HttpStatus.BAD_REQUEST);

            return new ResponseEntity<EstadoDTO>(new EstadoDTO(estado.get(0)), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Estado estado = repository.findById(id).get();
            repository.delete(estado);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<Mensaje>(
                    new Mensaje("No puede eliminar el estado, esta asignado a alguna incidencia."),
                    HttpStatus.valueOf(402));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
