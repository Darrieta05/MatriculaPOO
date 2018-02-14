package com.ulacit.matriculas.matriculasulacit.Modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "alumno")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true, allowSetters = true)

public class Alumno extends Persona{
    
    private Carrera carrera;
    private String Beca;
    
    public Alumno()
    {
        super();
    }
    
    @Override
    public String toString() {
        return  "Alumno{" +
                "idAlumno=" + getIdPersona() +
                ", idCarrera='" + carrera + '\'' +
                ", beca='" + Beca + '\'' +
                '}';
    }
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCarrera")
    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }
    
    @Column(name = "beca")
    public String getBeca() {
        return Beca;
    }

    public void setBeca(String Beca) {
        this.Beca = Beca;
    }
}
