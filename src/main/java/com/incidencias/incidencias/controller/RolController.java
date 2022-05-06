package com.incidencias.incidencias.controller;

import com.incidencias.incidencias.entity.Rol;
import com.incidencias.incidencias.repository.RolRepository;

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
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    @Qualifier("RolRepository")
    private RolRepository repository;

    @PostMapping("/insert")
    public Rol insert(@RequestBody Rol rol) {
        try {
            repository.save(rol);
            return rol;
        } catch (Exception ex) {
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public Rol update(@RequestBody Rol rol, @PathVariable Integer id) {
        try {
            Rol rl = repository.findById(id).get();
            rl.setNombre(rol.getNombre());
            repository.save(rl);
            return rl;
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/all")
    public Iterable<Rol> getAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public Rol getOne(@PathVariable Integer id) {
        try {
            return repository.findById(id).get();
        } catch (Exception ex) {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public Rol delete(@PathVariable Integer id) {
        try {
            Rol rol = repository.findById(id).get();
            repository.delete(rol);
            return rol;
        } catch (Exception ex) {
            return null;
        }
    }
}
