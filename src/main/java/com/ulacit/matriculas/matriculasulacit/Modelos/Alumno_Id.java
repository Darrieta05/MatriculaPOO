package com.ulacit.matriculas.matriculasulacit.Modelos;

import java.io.Serializable;
import java.util.*;
import javax.persistence.*;

@Embeddable
public class Alumno_Id implements Serializable{
    
    @Column(name = "idAlumno")
    private Integer idAlumno;
    @JoinColumn(name = "idPersona")
    @ManyToOne(cascade=CascadeType.ALL)  
    //@PrimaryKeyJoinColumn(referencedColumnName = "idPersona")
    private Persona persona;
 
    public Alumno_Id() {
    }
 
    public Alumno_Id(Integer id_alumno, Persona id_persona) {
        this.idAlumno = id_alumno;
        this.persona = id_persona;
    }

    public Persona getPersona() {
        return persona;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }
 
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno_Id)) return false;
        Alumno_Id that = (Alumno_Id) o;
        return Objects.equals(getIdAlumno(), that.getIdAlumno()) &&
                Objects.equals(getPersona(), that.getPersona());
    }
 
    @Override
    public int hashCode() {
        return Objects.hash(getIdAlumno(), getPersona());
    }
}
