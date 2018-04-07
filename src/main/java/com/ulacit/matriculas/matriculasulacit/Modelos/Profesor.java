package com.ulacit.matriculas.matriculasulacit.Modelos;

import javax.persistence.*;


@Entity
public class Profesor{

    @EmbeddedId
    private Profesor_Id idProfesor;
    
    private String especialidad;

    public Profesor() {
        super();
    }

    public Profesor_Id getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(Profesor_Id idProfesor) {
        this.idProfesor = idProfesor;
    }
    
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

}
