package com.incidencias.incidencias.controller;

import com.incidencias.incidencias.dto.EstadoDTO;
import com.incidencias.incidencias.entity.Estado;
import com.incidencias.incidencias.repository.EstadoRepository;

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
@RequestMapping("/api/estado")
public class EstadoController {

    @Autowired
    @Qualifier("EstadoRepository")
    private EstadoRepository repository;

    @PostMapping("/insert")
    public Estado insert(@RequestBody Estado estado) {
        try {
            repository.save(estado);
            return estado;
        } catch (Exception ex) {
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public Estado update(@RequestBody Estado estado, @PathVariable Integer id) {
        try {
            Estado est = repository.findById(id).get();
            est.setCodigo(estado.getCodigo());
            est.setDescripcion(estado.getDescripcion());
            repository.save(est);
            return est;
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/all")
    public Iterable<Estado> getAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public Estado getOne(@PathVariable Integer id) {
        try {
            return repository.findById(id).get();
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/partial/{id}")
    public EstadoDTO getPartialOne(@PathVariable Integer id) {
        try {
            return new EstadoDTO(repository.findById(id).get());
        } catch (Exception ex) {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public Estado delete(@PathVariable Integer id) {
        try {
            Estado estado = repository.findById(id).get();
            repository.delete(estado);
            return estado;
        } catch (Exception ex) {
            return null;
        }
    }
}
