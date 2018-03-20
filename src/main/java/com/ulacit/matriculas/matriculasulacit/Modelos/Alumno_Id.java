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
 
}
