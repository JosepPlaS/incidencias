package com.incidencias.incidencias.controller;

import java.util.ArrayList;

import com.incidencias.incidencias.dto.TipoHardwareDTO;
import com.incidencias.incidencias.entity.TipoHardware;
import com.incidencias.incidencias.repository.TipoHardwareRepository;

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
@RequestMapping("/api/tipo_hardware")
public class TipoHardwareController {

    @Autowired
    @Qualifier("TipoHardwareRepository")
    private TipoHardwareRepository repository;

    @PostMapping("/insert")
    public ResponseEntity insert(@RequestBody TipoHardware tipo_hardware) {
        try {
            repository.save(tipo_hardware);
            return new ResponseEntity<TipoHardware>(repository.findById(tipo_hardware.getId()).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody TipoHardware tipo_hardware, @PathVariable Integer id) {
        try {
            TipoHardware th = repository.findById(id).get();

            if (tipo_hardware.getNombre() != null)
                th.setNombre(tipo_hardware.getNombre());

            repository.save(th);
            return new ResponseEntity<TipoHardware>(th, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity getAll() {
        try {
            ArrayList<TipoHardwareDTO> tipos_hardware = new ArrayList<TipoHardwareDTO>();

            for (TipoHardware th : repository.findAll())
                tipos_hardware.add(new TipoHardwareDTO(th));

            return new ResponseEntity<Iterable<TipoHardwareDTO>>(tipos_hardware, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        try {
            return new ResponseEntity<TipoHardware>(repository.findById(id).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            TipoHardware tipo_hardware = repository.findById(id).get();
            repository.delete(tipo_hardware);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
