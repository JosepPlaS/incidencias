package com.incidencias.incidencias.controller;

import com.incidencias.incidencias.dto.LoginDTO;
import com.incidencias.incidencias.dto.Mensaje;
import com.incidencias.incidencias.dto.ProfesorDTO;
import com.incidencias.incidencias.entity.Profesor;
import com.incidencias.incidencias.repository.ProfesorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    @Qualifier("ProfesorRepository")
    private ProfesorRepository repository;

    @PostMapping("/login")
    public ResponseEntity insert(@RequestBody Profesor profesor) {
        try {
            for (Profesor pro : repository.findByEmail(profesor.getEmail()))
                if (pro.getContrasena().equals(profesor.getContrasena())) {
                    return new ResponseEntity<LoginDTO>(new LoginDTO(repository.findById(pro.getId()).get()),
                            HttpStatus.OK);
                }
            return new ResponseEntity<Mensaje>(new Mensaje("Email o contraseña incorrectos."), HttpStatus.valueOf(401));
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
