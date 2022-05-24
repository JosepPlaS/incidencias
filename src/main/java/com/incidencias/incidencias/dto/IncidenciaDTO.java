package com.incidencias.incidencias.dto;

import java.io.Serializable;
import java.sql.Date;

import com.incidencias.incidencias.entity.Incidencia;

public class IncidenciaDTO implements Serializable {
    private Integer id;
    private Integer tipo_incidencia;
    private Date fecha_incidencia;
    private Date fecha_introduccion;
    private String aula;
    private String descripcion;
    private String observaciones;
    private Date fecha_finalizacion;
    private Integer tiempo_invertido;
    private String historial;
    private EstadoDTO estado;
    private ProfesorDTO reportador;
    private ProfesorDTO responsable;

    public IncidenciaDTO(Incidencia incidencia) {
        this.id = incidencia.getId();
        this.tipo_incidencia = incidencia.getTipo_incidencia();
        this.fecha_incidencia = incidencia.getFecha_incidencia();
        this.fecha_introduccion = incidencia.getFecha_introduccion();
        this.aula = incidencia.getAula();
        this.descripcion = incidencia.getDescripcion();
        this.observaciones = incidencia.getObservaciones();
        this.fecha_finalizacion = incidencia.getFecha_finalizacion();
        this.tiempo_invertido = incidencia.getTiempo_invertido();
        this.historial = incidencia.getHistorial();
        this.estado = new EstadoDTO(incidencia.getEstado());
        if (incidencia.getReportador() != null)
            this.reportador = new ProfesorDTO(incidencia.getReportador());
        if (incidencia.getResponsable() != null)
            this.responsable = new ProfesorDTO(incidencia.getResponsable());
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

    public EstadoDTO getEstado() {
        return estado;
    }

    public void setEstado(EstadoDTO estado) {
        this.estado = estado;
    }

    public ProfesorDTO getReportador() {
        return reportador;
    }

    public void setReportador(ProfesorDTO reportador) {
        this.reportador = reportador;
    }

    public ProfesorDTO getResponsable() {
        return responsable;
    }

    public void setResponsable(ProfesorDTO responsable) {
        this.responsable = responsable;
    }
}
