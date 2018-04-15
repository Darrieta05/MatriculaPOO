package com.ulacit.matriculas.matriculasulacit.Modelos;

import javax.persistence.*;

@Entity
public class Carrera {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCarrera;
    private String codigo;
    private String nombre;
    private Integer totalCreditos;



    public Carrera() {
        super();
    }

    public int getIdCarrera() {
        return idCarrera;
    }

    public void setIdCarrera(int idCarrera) {
        this.idCarrera = idCarrera;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTotalCreditos() {
        return totalCreditos;
    }

    public void setTotalCreditos(Integer totalCreditos) {
        this.totalCreditos = totalCreditos;
    }
}
