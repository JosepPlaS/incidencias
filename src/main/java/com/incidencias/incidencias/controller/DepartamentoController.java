package com.incidencias.incidencias.controller;

import java.util.ArrayList;

import com.incidencias.incidencias.dto.DepartamentoDTO;
import com.incidencias.incidencias.entity.Departamento;
import com.incidencias.incidencias.repository.DepartamentoRepository;

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
@RequestMapping("/api/departamento")
public class DepartamentoController {

    @Autowired
    @Qualifier("DepartamentoRepository")
    private DepartamentoRepository repository;

    @PostMapping("/insert")
    public ResponseEntity insert(@RequestBody Departamento departamento) {
        try {
            repository.save(departamento);
            return new ResponseEntity<Departamento>(repository.findById(departamento.getId()).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody Departamento departamento, @PathVariable Integer id) {
        try {
            Departamento dep = repository.findById(id).get();

            if (departamento.getCodigo() != null)
                dep.setCodigo(departamento.getCodigo());
            if (departamento.getNombre() != null)
                dep.setNombre(departamento.getNombre());
            if (departamento.getDescripcion() != null)
                dep.setDescripcion(departamento.getDescripcion());

            repository.save(dep);
            return new ResponseEntity<Departamento>(dep, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/all")
    public ResponseEntity getAll() {
        try {
            ArrayList<DepartamentoDTO> departamentos = new ArrayList<DepartamentoDTO>();

            for (Departamento dep : repository.findAll())
                departamentos.add(new DepartamentoDTO(dep));

            return new ResponseEntity<Iterable<DepartamentoDTO>>(departamentos, HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity getOne(@PathVariable Integer id) {
        try {
            return new ResponseEntity<Departamento>(repository.findById(id).get(), HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Integer id) {
        try {
            Departamento departamento = repository.findById(id).get();
            repository.delete(departamento);
            return new ResponseEntity<Departamento>(HttpStatus.OK);
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}
