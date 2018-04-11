package com.ulacit.matriculas.matriculasulacit.Modelos;

import java.io.Serializable;
import javax.persistence.*;

/*Esto es un ejemplo del patron Dependency Injection
Mediante las anotaciones que brinda spring framework se crea la inyeccion de dependencia
de la cual se vaya a usar, en este caso se crea la anotacion de
@Entity para referenciar que el mismo es una entidad/modelo*/
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
