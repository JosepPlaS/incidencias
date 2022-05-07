package com.incidencias.incidencias.controller;

import java.util.ArrayList;

import com.incidencias.incidencias.dto.PermisoDTO;
import com.incidencias.incidencias.entity.Permiso;
import com.incidencias.incidencias.repository.PermisoRepository;

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
@RequestMapping("/api/permiso")
public class PermisoController {

    @Autowired
    @Qualifier("PermisoRepository")
    private PermisoRepository repository;

    @PostMapping("/insert")
    public ResponseEntity insert(@RequestBody Permiso permiso) {
        try {
            repository.save(permiso);
            return new ResponseEntity<Permiso>(repository.findById(permiso.getId()).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Permiso permiso, @PathVariable Integer id) {
        try {
            Permiso perm = repository.findById(id).get();

            if (permiso.getCodigo() != null)
                perm.setCodigo(permiso.getCodigo());
            if (permiso.getDescripcion() != null)
                perm.setDescripcion(permiso.getDescripcion());

            repository.save(perm);
            return new ResponseEntity<Permiso>(perm, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity getAll() {
        try {
            ArrayList<PermisoDTO> permisos = new ArrayList<PermisoDTO>();

            for (Permiso perm : repository.findAll())
                permisos.add(new PermisoDTO(perm));

            return new ResponseEntity<Iterable<PermisoDTO>>(permisos, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Permiso>(repository.findById(id).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            Permiso perm = repository.findById(id).get();
            repository.delete(perm);
            return new ResponseEntity<Permiso>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
