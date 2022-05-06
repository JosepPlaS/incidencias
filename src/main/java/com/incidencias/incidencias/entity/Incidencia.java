package com.incidencias.incidencias.entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Table(name = "incidencia")
@Entity
public class Incidencia implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private Integer id;
    @Column(name = "tipo_incidencia")
    private String tipo_incidencia;
    @Column(name = "fecha_incidencia")
    private Date fecha_incidencia;
    @Column(name = "fecha_introduccion")
    private Date fecha_introduccion;
    @Column(name = "aula")
    private String aula;
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "observaciones")
    private String observaciones;
    @Column(name = "fecha_finalizacion")
    private Date fecha_finalizacion;
    @Column(name = "tiempo_invertido")
    private Time tiempo_invertido;
    @Column(name = "modelo")
    private String modelo;
    @Column(name = "numero_serie")
    private String numero_serie;
    @Column(name = "sistema_operativo")
    private String sistema_operativo;
    @Column(name = "aplicacion")
    private String aplicacion;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    @JsonIgnoreProperties(value = { "incidencias" })
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "tipo_hardware_id")
    @JsonIgnoreProperties(value = { "incidencias" })
    private TipoHardware tipo_hardware;

    @ManyToOne
    @JoinColumn(name = "departamento_id")
    @JsonIgnoreProperties(value = { "incidencias", "profesores" })
    private Departamento departamento;

    @ManyToOne
    @JoinColumn(name = "reportador_id")
    @JsonIgnoreProperties(value = { "incidencias_reportadas", "incidencias_responsable", "departamento" })
    private Profesor reportador;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    @JsonIgnoreProperties(value = { "incidencias_responsable", "incidencias_reportadas", "departamento" })
    private Profesor responsable;

    public Incidencia() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipo_incidencia() {
        return tipo_incidencia;
    }

    public void setTipo_incidencia(String tipo_incidencia) {
        this.tipo_incidencia = tipo_incidencia;
    }

    public Date getFecha_incidencia() {
        return fecha_incidencia;
    }

    public void setFecha_incidencia(Date fecha_incidencia) {
        this.fecha_incidencia = fecha_incidencia;
    }

    public Date getFecha_introduccion() {
        return fecha_introduccion;
    }

    public void setFecha_introduccion(Date fecha_introduccion) {
        this.fecha_introduccion = fecha_introduccion;
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Date getFecha_finalizacion() {
        return fecha_finalizacion;
    }

    public void setFecha_finalizacion(Date fecha_finalizacion) {
        this.fecha_finalizacion = fecha_finalizacion;
    }

    public Time getTiempo_invertido() {
        return tiempo_invertido;
    }

    public void setTiempo_invertido(Time tiempo_invertido) {
        this.tiempo_invertido = tiempo_invertido;
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

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public TipoHardware getTipo_hardware() {
        return tipo_hardware;
    }

    public void setTipo_hardware(TipoHardware tipo_hardware) {
        this.tipo_hardware = tipo_hardware;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Profesor getReportador() {
        return reportador;
    }

    public void setReportador(Profesor reportador) {
        this.reportador = reportador;
    }

    public Profesor getResponsable() {
        return responsable;
    }

    public void setResponsable(Profesor responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "Incidencia [aplicacion=" + aplicacion + ", aula=" + aula + ", descripcion=" + descripcion
                + ", fecha_finalizacion=" + fecha_finalizacion + ", fecha_incidencia=" + fecha_incidencia
                + ", fecha_introduccion=" + fecha_introduccion + ", id=" + id + ", modelo=" + modelo + ", numero_serie="
                + numero_serie + ", observaciones=" + observaciones + ", sistema_operativo=" + sistema_operativo
                + ", tiempo_invertido=" + tiempo_invertido + ", tipo_incidencia=" + tipo_incidencia + "]";
    }
}
