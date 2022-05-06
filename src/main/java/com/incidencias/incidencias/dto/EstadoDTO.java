package com.incidencias.incidencias.dto;

import java.io.Serializable;

import com.incidencias.incidencias.entity.Estado;

public class EstadoDTO implements Serializable {
    private Integer id;
    private String codigo;
    private String descripcion;

    public EstadoDTO() {

    }

    public EstadoDTO(Estado estado) {
        this.id = estado.getId();
        this.codigo = estado.getCodigo();
        this.descripcion = estado.getDescripcion();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
