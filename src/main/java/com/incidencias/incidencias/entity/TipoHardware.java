package com.incidencias.incidencias.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "tipo_hardware")
@Entity
public class TipoHardware implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "nombre", unique = true)
    private String nombre;

    @OneToMany(mappedBy = "tipo_hardware")
    @JsonIgnoreProperties(value = { "tipo_hardware" }, allowSetters = true)
    private List<IncidenciaHW> incidencias_hardware;

    public TipoHardware() {

    }

    public TipoHardware(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<IncidenciaHW> getIncidencias_hardware() {
        return incidencias_hardware;
    }

    public void setIncidencias_hardware(List<IncidenciaHW> incidencias_hardware) {
        this.incidencias_hardware = incidencias_hardware;
    }
}
