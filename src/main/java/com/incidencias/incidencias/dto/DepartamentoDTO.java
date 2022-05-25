package com.incidencias.incidencias.dto;

import java.io.Serializable;

import com.incidencias.incidencias.entity.Departamento;

public class DepartamentoDTO implements Serializable {
    private Integer id;
    private String codigo;
    private String nombre;
    private String localizacion;

    public DepartamentoDTO() {

    }

    public DepartamentoDTO(Departamento departamento) {
        this.id = departamento.getId();
        this.codigo = departamento.getCodigo();
        this.nombre = departamento.getNombre();
        this.localizacion = departamento.getLocalizacion();
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalizacion() {
        return localizacion;
    }

    public void setLocalizacion(String localizacion) {
        this.localizacion = localizacion;
    }

}
