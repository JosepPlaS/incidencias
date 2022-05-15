package com.incidencias.incidencias.controller;

import java.util.ArrayList;

import com.incidencias.incidencias.dto.Mensaje;
import com.incidencias.incidencias.dto.RolDTO;
import com.incidencias.incidencias.entity.Rol;
import com.incidencias.incidencias.repository.RolRepository;

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
@RequestMapping("/api/rol")
public class RolController {

    @Autowired
    @Qualifier("RolRepository")
    private RolRepository repository;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Rol rol) {
        try {
            repository.save(rol);
            return new ResponseEntity<Rol>(repository.findById(rol.getId()).get(), HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<Mensaje>(new Mensaje("No puede crear el rol, nombre repetido."),
                    HttpStatus.valueOf(402));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Rol rol, @PathVariable Integer id) {
        try {
            Rol rl = repository.findById(id).get();

            if (rl.getNombre() != null)
                rl.setNombre(rol.getNombre());
            if (rl.getPermisos() != null)
                rl.setPermisos(rol.getPermisos());

            repository.save(rl);
            return new ResponseEntity<Rol>(rl, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<Mensaje>(new Mensaje("No puede modificar el rol, nombre repetido."),
                    HttpStatus.valueOf(402));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            ArrayList<RolDTO> roles = new ArrayList<RolDTO>();

            for (Rol rl : repository.findAll())
                roles.add(new RolDTO(rl));

            return new ResponseEntity<Iterable<RolDTO>>(roles, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Rol>(repository.findById(id).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Rol rol = repository.findById(id).get();
            repository.delete(rol);
            return new ResponseEntity<Rol>(HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<Mensaje>(new Mensaje("No puede eliminar el rol, pertenece a algun profesor."),
                    HttpStatus.valueOf(402));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
