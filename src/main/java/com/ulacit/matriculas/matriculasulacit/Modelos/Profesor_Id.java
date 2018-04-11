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
 
    public Profesor_Id(int idProfesor, Persona id_persona) {
        this.idProfesor = idProfesor;
        this.persona = id_persona;
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
