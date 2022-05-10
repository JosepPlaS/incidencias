package com.incidencias.incidencias.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "profesor")
@Entity
public class Profesor implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "dni")
    private String dni;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido1")
    private String apellido1;
    @Column(name = "apellido2")
    private String apellido2;
    @Column(name = "email")
    private String email;
    @Column(name = "contrasena")
    private String contrasena;

    @OneToMany(mappedBy = "reportador")
    @JsonIgnoreProperties(value = { "reportador", "responsable" }, allowSetters = true)
    private List<Incidencia> incidencias_reportadas;

    @OneToMany(mappedBy = "responsable")
    @JsonIgnoreProperties(value = { "responsable", "reportador" }, allowSetters = true)
    private List<Incidencia> incidencias_responsable;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    @JsonIgnoreProperties(value = { "profesores" }, allowSetters = true)
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "rol_id")
    @JsonIgnoreProperties(value = { "profesores" }, allowSetters = true)
    private Rol rol;

    public Profesor() {

    }

    public Profesor(Integer id, String email, String contrasena, Rol rol) {
        this.id = id;
        this.email = email;
        this.contrasena = contrasena;
        this.rol = rol;
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

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Incidencia> getIncidencias_reportadas() {
        return incidencias_reportadas;
    }

    public void setIncidencias_reportadas(List<Incidencia> incidencias_reportadas) {
        this.incidencias_reportadas = incidencias_reportadas;
    }

    public List<Incidencia> getIncidencias_responsable() {
        return incidencias_responsable;
    }

    public void setIncidencias_responsable(List<Incidencia> incidencias_responsable) {
        this.incidencias_responsable = incidencias_responsable;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

}
