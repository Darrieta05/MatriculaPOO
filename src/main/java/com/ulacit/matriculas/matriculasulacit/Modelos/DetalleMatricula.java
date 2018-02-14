package com.ulacit.matriculas.matriculasulacit.Modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "detalleMatricula")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true, allowSetters = true)

public class DetalleMatricula {
    
    private Integer idDetalleMatricula;
    private Matricula matricula;
    private Materia materia;
    
    public DetalleMatricula()
    {
        super();
    }
    
    @Override
    public String toString() {
        return "DetalleMatricula{" +
                "idDetalleMatricula=" + idDetalleMatricula +
                ", idMatricula='" + matricula + '\'' +
                ", idMateria='" + materia + '\'' +
                '}';
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdDetalleMatricula() {
        return idDetalleMatricula;
    }

    public void setIdDetalleMatricula(Integer idDetalleMatricula) {
        this.idDetalleMatricula = idDetalleMatricula;
    }
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idMatricula")
    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idMateria")
    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
    
}
