package com.ulacit.matriculas.matriculasulacit.Modelos;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAula;
    private String tipo;
    private String area;
    private String numeroAula;

    public Aula() {
        super();
    }

    public int getIdAula() {
        return idAula;
    }

    public void setIdAula(int idAula) {
        this.idAula = idAula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getNumeroAula() {
        return numeroAula;
    }

    public void setNumeroAula(String numeroAula) {
        this.numeroAula = numeroAula;
    }
}
