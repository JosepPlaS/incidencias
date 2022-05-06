package com.incidencias.incidencias.controller;

import com.incidencias.incidencias.entity.TipoHardware;
import com.incidencias.incidencias.repository.TipoHardwareRepository;

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
@RequestMapping("/api/tipo_hardware")
public class TipoHardwareController {

    @Autowired
    @Qualifier("TipoHardwareRepository")
    private TipoHardwareRepository repository;

    @PostMapping("/insert")
    public TipoHardware insert(@RequestBody TipoHardware tipo_hardware) {
        try {
            repository.save(tipo_hardware);
            return tipo_hardware;
        } catch (Exception ex) {
            return null;
        }
    }

    @PutMapping("/update/{id}")
    public TipoHardware update(@RequestBody TipoHardware tipo_hardware, @PathVariable Integer id) {
        try {
            TipoHardware rl = repository.findById(id).get();
            rl.setNombre(tipo_hardware.getNombre());
            repository.save(rl);
            return rl;
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/all")
    public Iterable<TipoHardware> getAll() {
        try {
            return repository.findAll();
        } catch (Exception ex) {
            return null;
        }
    }

    @GetMapping("/get/{id}")
    public TipoHardware getOne(@PathVariable Integer id) {
        try {
            return repository.findById(id).get();
        } catch (Exception ex) {
            return null;
        }
    }

    @DeleteMapping("/delete/{id}")
    public TipoHardware delete(@PathVariable Integer id) {
        try {
            TipoHardware tipo_hardware = repository.findById(id).get();
            repository.delete(tipo_hardware);
            return tipo_hardware;
        } catch (Exception ex) {
            return null;
        }
    }
}
