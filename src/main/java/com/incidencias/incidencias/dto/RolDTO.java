package com.incidencias.incidencias.dto;

import java.io.Serializable;

import com.incidencias.incidencias.entity.Permiso;
import com.incidencias.incidencias.entity.Rol;

public class RolDTO implements Serializable {
    private Integer id;
    private String nombre;
    private Iterable<Permiso> permisos;

    public RolDTO() {

    }

    public RolDTO(Rol rol) {
        this.id = rol.getId();
        this.nombre = rol.getNombre();
        this.permisos = rol.getPermisos();
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

    public Iterable<Permiso> getPermisos() {
        return permisos;
    }

    public void setPermisos(Iterable<Permiso> permisos) {
        this.permisos = permisos;
    }
}
