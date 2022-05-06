package com.incidencias.incidencias.controller;

import com.incidencias.incidencias.entity.Departamento;
import com.incidencias.incidencias.repository.DepartamentoRepository;

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
@RequestMapping("/api/departamento")
public class DepartamentoController {

    @Autowired
    @Qualifier("DepartamentoRepository")
    private DepartamentoRepository repository;

    @PostMapping("/insert")
    public Departamento insert(@RequestBody Departamento departamento) {
        try {
            repository.save(departamento);
            return departamento;
        } catch (Exception ex) {
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public Departamento update(@RequestBody Departamento departamento, @PathVariable Integer id) {
        try {
            Departamento dep = repository.findById(id).get();
            dep.setCodigo(departamento.getCodigo());
            dep.setNombre(departamento.getNombre());
            dep.setDescripcion(departamento.getDescripcion());
            repository.save(dep);
            return dep;
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/all")
    public Iterable<Departamento> getAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public Departamento getOne(@PathVariable Integer id) {
        try {
            return repository.findById(id).get();
        } catch (Exception ex) {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public Departamento delete(@PathVariable Integer id) {
        try {
            Departamento departamento = repository.findById(id).get();
            repository.delete(departamento);
            return departamento;
        } catch (Exception ex) {
            return null;
        }
    }
}
