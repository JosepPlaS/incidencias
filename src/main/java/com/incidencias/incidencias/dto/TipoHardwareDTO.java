package com.incidencias.incidencias.dto;

import java.io.Serializable;

import com.incidencias.incidencias.entity.TipoHardware;

public class TipoHardwareDTO implements Serializable {
    private Integer id;
    private String nombre;

    public TipoHardwareDTO() {

    }

    public TipoHardwareDTO(TipoHardware tipo_hardware) {
        this.id = tipo_hardware.getId();
        this.nombre = tipo_hardware.getNombre();
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
}
