package com.incidencias.incidencias.dto;

import java.io.Serializable;

import com.incidencias.incidencias.entity.Profesor;

public class LoginDTO implements Serializable {
    private Integer id;
    private String email;
    private String dni;
    private RolDTO rol;

    public LoginDTO() {

    }

    public LoginDTO(Profesor profesor) {
        this.id = profesor.getId();
        this.email = profesor.getEmail();
        this.dni = profesor.getDni();
        this.rol = new RolDTO(profesor.getRol());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public RolDTO getRol() {
        return rol;
    }

    public void setRol(RolDTO rol) {
        this.rol = rol;
    }
}
