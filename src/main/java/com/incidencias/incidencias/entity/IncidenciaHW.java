package com.incidencias.incidencias.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "incidencia_hw")
@Entity
public class IncidenciaHW implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "numero_serie")
    private String numero_serie;

    @ManyToOne
    @JoinColumn(name = "tipo_hardware_id")
    @JsonIgnoreProperties(value = { "incidencias_hardware" }, allowSetters = true)
    private TipoHardware tipo_hardware;

    @OneToOne(mappedBy = "incidencia_hw")
    @JsonIgnore
    private Incidencia incidencia;

    public IncidenciaHW() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNumero_serie() {
        return numero_serie;
    }

    public void setNumero_serie(String numero_serie) {
        this.numero_serie = numero_serie;
    }

    public TipoHardware getTipo_hardware() {
        return tipo_hardware;
    }

    public void setTipo_hardware(TipoHardware tipo_hardware) {
        this.tipo_hardware = tipo_hardware;
    }

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }

}
