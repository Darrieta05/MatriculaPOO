package com.ulacit.matriculas.matriculasulacit.Modelos;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.util.Date;
import javax.persistence.*;

@Entity
public class Persona{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPersona;
    private Integer cedula;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String sexo;

    /*Audit fields*/
    //@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date fechaCreacion;
    //@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date fechaActualizacion;
    private int creadoPor;
    private  int actualizadoPor;
    private Boolean eliminado = false;


    public Persona(){
        super();
    }

    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
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
