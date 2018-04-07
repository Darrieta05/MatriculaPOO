package com.ulacit.matriculas.matriculasulacit.Modelos;

import javax.persistence.*;
import java.util.Date;

@Entity
public class DetalleMatricula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idDetalleMatricula;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="idMatricula")
    private Matricula matricula;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="idMateria")
    private Materia materia;


    public DetalleMatricula()
    {
        super();
    }

    public Integer getIdDetalleMatricula() {
        return idDetalleMatricula;
    }

    public void setIdDetalleMatricula(Integer idDetalleMatricula) {
        this.idDetalleMatricula = idDetalleMatricula;
    }

    public Matricula getMatricula() {
        return matricula;
    }

    public void setMatricula(Matricula matricula) {
        this.matricula = matricula;
    }

    public Materia getMateria() {
        return materia;
    }

    public void setMateria(Materia materia) {
        this.materia = materia;
    }
}
