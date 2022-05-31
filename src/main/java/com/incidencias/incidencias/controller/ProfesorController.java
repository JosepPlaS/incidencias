package com.incidencias.incidencias.controller;

import java.util.ArrayList;

import com.incidencias.incidencias.dto.Mensaje;
import com.incidencias.incidencias.dto.ProfesorDTO;
import com.incidencias.incidencias.entity.Profesor;
import com.incidencias.incidencias.repository.ProfesorRepository;

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
@RequestMapping("/api/profesor")
public class ProfesorController {

    @Autowired
    @Qualifier("ProfesorRepository")
    private ProfesorRepository repository;

    @PostMapping("/insert")
    public ResponseEntity<?> insert(@RequestBody Profesor profesor) {
        try {
            if (profesor.getDepartamento().getCodigo() == null) {
                profesor.setDepartamento(null);
            }
            repository.save(profesor);
            return new ResponseEntity<Profesor>(repository.findById(profesor.getId()).get(), HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<Mensaje>(new Mensaje("No puede crear el profesor, DNI repetido."),
                    HttpStatus.valueOf(402));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@RequestBody Profesor profesor, @PathVariable Integer id) {
        try {
            Profesor pro = repository.findById(id).get();

            if (profesor.getDni() != null)
                pro.setDni(profesor.getDni());
            if (profesor.getNombre() != null)
                pro.setNombre(profesor.getNombre());
            if (profesor.getApellido1() != null)
                pro.setApellido1(profesor.getApellido1());
            if (profesor.getApellido2() != null)
                pro.setApellido2(profesor.getApellido2());
            if (profesor.getEmail() != null)
                pro.setEmail(profesor.getEmail());
            if (profesor.getContrasena() != null)
                pro.setContrasena(profesor.getContrasena());

            // RELACIONES
            pro.setDepartamento(profesor.getDepartamento());
            if (profesor.getRol() != null)
                pro.setRol(profesor.getRol());

            repository.save(pro);
            return new ResponseEntity<Profesor>(pro, HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<Mensaje>(new Mensaje("No puede modificar el profesor, DNI repetido."),
                    HttpStatus.valueOf(402));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity<?> getAll() {
        try {
            ArrayList<ProfesorDTO> profesores = new ArrayList<ProfesorDTO>();

            for (Profesor profe : repository.findAll())
                if (!profe.getEmail().equals("iscaincidencias@gmail.com"))
                    profesores.add(new ProfesorDTO(profe));

            return new ResponseEntity<Iterable<ProfesorDTO>>(profesores, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getOne(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Profesor>(repository.findById(id).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get_dto/{id}")
    public ResponseEntity<?> getOneDto(@PathVariable Integer id) {
        try {
            return new ResponseEntity<ProfesorDTO>(new ProfesorDTO(repository.findById(id).get()), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        try {
            Profesor profesor = repository.findById(id).get();
            repository.delete(profesor);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (DataIntegrityViolationException ex) {
            return new ResponseEntity<Mensaje>(
                    new Mensaje("No puede eliminar el profesor, tiene asignada alguna incidencia."),
                    HttpStatus.valueOf(402));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
