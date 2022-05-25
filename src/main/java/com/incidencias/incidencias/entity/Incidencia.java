package com.incidencias.incidencias.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
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
    private Integer tipo_incidencia;
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
    private Integer tiempo_invertido;
    @Column(name = "historial", columnDefinition = "Text")
    private String historial;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    @JsonIgnoreProperties(value = { "incidencias" })
    private Estado estado;

    @ManyToOne
    @JoinColumn(name = "reportador_id")
    @JsonIgnoreProperties(value = { "incidencias_reportadas", "incidencias_responsable",
            "departamento" }, allowSetters = true)
    private Profesor reportador;

    @ManyToOne
    @JoinColumn(name = "responsable_id")
    @JsonIgnoreProperties(value = { "incidencias_responsable", "incidencias_reportadas",
            "departamento" }, allowSetters = true)
    private Profesor responsable;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "incidencia_sw_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "incidencia" }, allowSetters = true)
    private IncidenciaSW incidencia_sw;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "incidencia_hw_id", referencedColumnName = "id")
    @JsonIgnoreProperties(value = { "incidencia" }, allowSetters = true)
    private IncidenciaHW incidencia_hw;

    public Incidencia() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTipo_incidencia() {
        return tipo_incidencia;
    }

    public void setTipo_incidencia(Integer tipo_incidencia) {
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

    public Integer getTiempo_invertido() {
        return tiempo_invertido;
    }

    public void setTiempo_invertido(Integer tiempo_invertido) {
        this.tiempo_invertido = tiempo_invertido;
    }

    public String getHistorial() {
        return historial;
    }

    public void setHistorial(String historial) {
        this.historial = historial;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
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

    public IncidenciaSW getIncidencia_sw() {
        return incidencia_sw;
    }

    public void setIncidencia_sw(IncidenciaSW incidencia_sw) {
        this.incidencia_sw = incidencia_sw;
    }

    public IncidenciaHW getIncidencia_hw() {
        return incidencia_hw;
    }

    public void setIncidencia_hw(IncidenciaHW incidencia_hw) {
        this.incidencia_hw = incidencia_hw;
    }

    public String getCodigoIncidencia() {
        if (id > 999) {
            return id + "";
        } else if (id > 99) {
            return "0" + id;
        } else if (id > 9) {
            return "00" + id;
        } else {
            return "000" + id;
        }
    }

    public Integer getTipoIncidencia() {
        return tipo_incidencia;
    }

}
