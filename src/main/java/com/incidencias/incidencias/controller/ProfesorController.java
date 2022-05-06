package com.incidencias.incidencias.controller;

import com.incidencias.incidencias.entity.Profesor;
import com.incidencias.incidencias.repository.ProfesorRepository;

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
@RequestMapping("/api/profesor")
public class ProfesorController {

    @Autowired
    @Qualifier("ProfesorRepository")
    private ProfesorRepository repository;

    @PostMapping("/insert")
    public Profesor insert(@RequestBody Profesor profesor) {
        try {
            repository.save(profesor);
            return profesor;
        } catch (Exception ex) {
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public Profesor update(@RequestBody Profesor profesor, @PathVariable Integer id) {
        try {
            Profesor pro = repository.findById(id).get();
            pro.setDni(profesor.getDni());
            pro.setNombre(profesor.getNombre());
            pro.setApellido1(profesor.getApellido1());
            pro.setApellido2(profesor.getApellido2());
            pro.setEmail(profesor.getEmail());
            pro.setContrasena(profesor.getContrasena());

            // RELACIONES
            pro.setDepartamento(profesor.getDepartamento());
            pro.setRol(profesor.getRol());

            repository.save(pro);
            return pro;
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/all")
    public Iterable<Profesor> getAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public Profesor getOne(@PathVariable Integer id) {
        try {
            return repository.findById(id).get();
        } catch (Exception ex) {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public Profesor delete(@PathVariable Integer id) {
        try {
            Profesor profesor = repository.findById(id).get();
            repository.delete(profesor);
            return profesor;
        } catch (Exception ex) {
            return null;
        }
    }
}
