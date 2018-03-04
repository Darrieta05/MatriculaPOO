package com.ulacit.matriculas.matriculasulacit.Modelos;

import javax.persistence.*;

@Entity
public class Profesor extends Persona {

    private int idProfesor;
    private String especialidad;

    public Profesor() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
