package com.incidencias.incidencias.dto;

import java.io.Serializable;

import com.incidencias.incidencias.entity.Profesor;

public class ProfesorDTO implements Serializable {
    private Integer id;
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String email;
    private DepartamentoDTO departamento;
    private RolDTO rol;

    public ProfesorDTO() {

    }

    public ProfesorDTO(Profesor profesor) {
        this.id = profesor.getId();
        this.dni = profesor.getDni();
        this.nombre = profesor.getNombre();
        this.apellido1 = profesor.getApellido1();
        this.apellido2 = profesor.getApellido2();
        this.email = profesor.getEmail();
        if (profesor.getDepartamento() != null)
            this.departamento = new DepartamentoDTO(profesor.getDepartamento());
        if (profesor.getRol() != null)
            this.rol = new RolDTO(profesor.getRol());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public DepartamentoDTO getDepartamento() {
        return departamento;
    }

    public void setDepartamento(DepartamentoDTO departamento) {
        this.departamento = departamento;
    }

    public RolDTO getRol() {
        return rol;
    }

    public void setRol(RolDTO rol) {
        this.rol = rol;
    }
}
