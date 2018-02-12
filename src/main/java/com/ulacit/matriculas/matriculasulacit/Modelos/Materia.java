package com.ulacit.matriculas.matriculasulacit.Modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "materia")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true, allowSetters = true)
public class Materia {

    private Integer idMateria;
    private String nombre;
    private Integer estatus;
    private Date fechaInicio;
    private Date fechaFin;
    private Carrera carrera;

    public Materia() {
        super();
    }

    @Override
    public String toString() {
        return "Materia{" +
                "idMateria=" + idMateria +
                ", nombre='" + nombre + '\'' +
                ", estatus=" + estatus +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", carrera=" + carrera +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "idMateria")
    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    @Column(name = "nombre")
    @NotNull
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "estatus")
    @NotNull
    public Integer getEstatus() {
        return estatus;
    }

    public void setEstatus(Integer estatus) {
        this.estatus = estatus;
    }

    @Column(name = "fechaInicio")
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    @Column(name = "fechaFin")
    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "idCarrera")
    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
}
