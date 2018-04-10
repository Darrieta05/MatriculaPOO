package com.ulacit.matriculas.matriculasulacit.Modelos;

import javax.persistence.*;


@Entity
public class Profesor{

    @EmbeddedId
    @MapsId("profesorKey")
    private Profesor_Id profesorKey;
    
    private String especialidad;

    public Profesor() {
        super();
    }

    public Profesor_Id getProfesorKey() {
        return profesorKey;
    }

    public void setProfesorKey(Profesor_Id profesorKey) {
        this.profesorKey = profesorKey;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
