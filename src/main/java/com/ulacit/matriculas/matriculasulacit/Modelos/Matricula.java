package com.ulacit.matriculas.matriculasulacit.Modelos;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Matricula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMatricula;
    private Date fecha;
    private Double monto;
    private Double total;
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
        @JoinColumn(
            name = "idAlumno",
            referencedColumnName = "idAlumno"),
        @JoinColumn(
            name = "idPersona",
            referencedColumnName = "idPersona")
    })
    private Alumno alumno;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    private Usuario usuario;

    /*Audit fields*/
    //@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date fechaCreacion;
    //@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date fechaActualizacion;
    private int creadoPor;
    private  int actualizadoPor;
    private Boolean eliminado = false;

    public Matricula() {
        super();
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Date getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(Date fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    public int getCreadoPor() {
        return creadoPor;
    }

    public void setCreadoPor(int creadoPor) {
        this.creadoPor = creadoPor;
    }

    public int getActualizadoPor() {
        return actualizadoPor;
    }

    public void setActualizadoPor(int actualizadoPor) {
        this.actualizadoPor = actualizadoPor;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
}
