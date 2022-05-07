package com.incidencias.incidencias.dto;

import java.io.Serializable;

import com.incidencias.incidencias.entity.Permiso;

public class PermisoDTO implements Serializable {
    private Integer id;
    private String codigo;
    private String descripcion;

    public PermisoDTO() {

    }

    public PermisoDTO(Permiso permiso) {
        this.id = permiso.getId();
        this.codigo = permiso.getCodigo();
        this.descripcion = permiso.getDescripcion();
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
