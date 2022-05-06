package com.incidencias.incidencias.controller;

import com.incidencias.incidencias.entity.Permiso;
import com.incidencias.incidencias.repository.PermisoRepository;

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
@RequestMapping("/api/permiso")
public class PermisoController {

    @Autowired
    @Qualifier("PermisoRepository")
    private PermisoRepository repository;

    @PostMapping("/insert")
    public Permiso insert(@RequestBody Permiso permiso) {
        try {
            repository.save(permiso);
            return permiso;
        } catch (Exception ex) {
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public Permiso update(@RequestBody Permiso permiso, @PathVariable Integer id) {
        try {
            Permiso perm = repository.findById(id).get();
            perm.setCodigo(permiso.getCodigo());
            perm.setDescripcion(permiso.getDescripcion());
            repository.save(perm);
            return perm;
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/all")
    public Iterable<Permiso> getAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public Permiso getOne(@PathVariable Integer id) {
        try {
            return repository.findById(id).get();
        } catch (Exception ex) {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public Permiso delete(@PathVariable Integer id) {
        try {
            Permiso perm = repository.findById(id).get();
            repository.delete(perm);
            return perm;
        } catch (Exception ex) {
            return null;
        }
    }
}
