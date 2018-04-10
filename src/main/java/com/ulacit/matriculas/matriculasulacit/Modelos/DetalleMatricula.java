package com.ulacit.matriculas.matriculasulacit.Modelos;

import javax.persistence.*;

@Entity
public class DetalleMatricula {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDetalleMatricula;

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

    public int getIdDetalleMatricula() {
        return idDetalleMatricula;
    }

    public void setIdDetalleMatricula(int idDetalleMatricula) {
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
