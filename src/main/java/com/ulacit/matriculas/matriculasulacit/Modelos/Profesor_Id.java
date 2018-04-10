package com.ulacit.matriculas.matriculasulacit.Modelos;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Embeddable
public class Profesor_Id implements Serializable{
    
    @Column(name = "idProfesor")
    private int idProfesor;
    @JoinColumn(name = "idPersona")
    @ManyToOne(cascade=CascadeType.ALL)
    private Persona persona;
 
    public Profesor_Id() {
    }
 
    public Profesor_Id(int id_alumno, Persona id_persona) {
        this.idProfesor = id_alumno;
        this.persona = id_persona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesor_Id)) return false;
        Profesor_Id that = (Profesor_Id) o;
        return idProfesor == that.idProfesor &&
                Objects.equals(persona, that.persona);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idProfesor, persona);
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
