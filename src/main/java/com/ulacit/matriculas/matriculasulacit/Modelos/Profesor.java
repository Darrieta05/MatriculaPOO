package com.ulacit.matriculas.matriculasulacit.Modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "Profesor")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true, allowSetters = true)
public class Profesor extends Persona{

    private String especialidad;

    public Profesor() {
        super();
    }

    @Override
    public String toString() {
        return  "Profesor{" +
                "idProfesor=" + getIdPersona() +
                ", especialidad='" + especialidad + '\'' +
                '}';
    }

    @Column(name = "especialidad")
    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }
}
