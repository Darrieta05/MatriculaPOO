package com.ulacit.matriculas.matriculasulacit.Modelos;

import java.io.Serializable;
import javax.persistence.*;

@Entity
public class Alumno implements Serializable{
    
    @EmbeddedId
    private Alumno_Id alumnoKey;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="idCarrera")
    private Carrera carrera;
    private String Beca;

    public Alumno() {
        super();
    }

    public Alumno_Id getAlumnoKey() {
        return alumnoKey;
    }

    public void setAlumnoKey(Alumno_Id alumnoKey) {
        this.alumnoKey = alumnoKey;
    }

    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public String getBeca() {
        return Beca;
    }

    public void setBeca(String beca) {
        Beca = beca;
    }
}
