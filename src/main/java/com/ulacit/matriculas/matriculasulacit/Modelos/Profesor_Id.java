package com.ulacit.matriculas.matriculasulacit.Modelos;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Embeddable
public class Profesor_Id implements Serializable{
    
    @Column(name = "idProfesor")
    private Integer idProfesor;
    @JoinColumn(name = "idPersona")
    @ManyToOne(cascade=CascadeType.ALL)  
    //@PrimaryKeyJoinColumn(referencedColumnName = "idPersona")
    private Persona persona;
 
    public Profesor_Id() {
    }
 
    public Profesor_Id(Integer id_alumno, Persona id_persona) {
        this.idProfesor = id_alumno;
        this.persona = id_persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public Integer getIdProfesor() {
        return idProfesor;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Profesor_Id)) return false;
        Profesor_Id that = (Profesor_Id) o;
        return Objects.equals(getIdProfesor(), that.getIdProfesor()) &&
                Objects.equals(getPersona(), that.getPersona());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getIdProfesor(), getPersona());
    }
}
