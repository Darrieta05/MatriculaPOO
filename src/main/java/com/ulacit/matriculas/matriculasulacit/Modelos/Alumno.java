package com.ulacit.matriculas.matriculasulacit.Modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Alumno implements Serializable{
    
    @EmbeddedId
    private Alumno_Id idAlumno;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="idCarrera")
    private Carrera carrera;
    private String Beca;

    public Alumno() {
        super();
    }

    public Alumno_Id getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno_Id idAlumno) {
        this.idAlumno = idAlumno;
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
