package com.incidencias.incidencias.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Table(name = "incidencia_sw")
@Entity
public class IncidenciaSW implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "sistema_operativo")
    private String sistema_operativo;
    @Column(name = "aplicacion")
    private String aplicacion;

    @OneToOne(mappedBy = "incidencia_sw")
    @JsonIgnore
    private Incidencia incidencia;

    public IncidenciaSW() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSistema_operativo() {
        return sistema_operativo;
    }

    public void setSistema_operativo(String sistema_operativo) {
        this.sistema_operativo = sistema_operativo;
    }

    public String getAplicacion() {
        return aplicacion;
    }

    public void setAplicacion(String aplicacion) {
        this.aplicacion = aplicacion;
    }

    public Incidencia getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(Incidencia incidencia) {
        this.incidencia = incidencia;
    }

}
