package com.ulacit.matriculas.matriculasulacit.Modelos;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

@Embeddable
public class Alumno_Id implements Serializable{

    @Column(name = "idAlumno")
    private int idAlumno;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPersona")
    private Persona persona;
 
    public Alumno_Id() {
    }

    public Alumno_Id(int idAlumno, Persona persona) {
        this.idAlumno = idAlumno;
        this.persona = persona;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Alumno_Id)) return false;
        Alumno_Id alumno_id = (Alumno_Id) o;
        return idAlumno == alumno_id.idAlumno &&
                Objects.equals(persona, alumno_id.persona);
    }

    @Override
    public int hashCode() {

        return Objects.hash(idAlumno, persona);
    }

    public int getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(int idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    
    public void setIdPersona(Integer idPersona)
    {
        this.persona.setIdPersona(idPersona);
    }
}
