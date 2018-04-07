package com.ulacit.matriculas.matriculasulacit.Modelos;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Materia {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer idMateria;
    private String nombre;
    private String codigo;
    private Double costo;
    private Integer creditos;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="idCarrera")
    private Carrera carrera;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="idAula")
    private Aula aula;

    public Materia() {
        super();
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public void setCreditos(Integer creditos) {
        this.creditos = creditos;
    }

}
