package com.ulacit.matriculas.matriculasulacit.Modelos;

import javax.persistence.*;

@Entity
public class Materia {


    private Integer idMateria;
    private String nombre;
    private String codigo;
    private Double costo;
    private Integer creditos;
    private Carrera carrera;
    private Aula aula;


    public Materia() {
        super();
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idCarrera")
    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAula")
    public Aula getAula() {
        return aula;
    }

    public void setAula(Aula aula) {
        this.aula = aula;
    }
}
