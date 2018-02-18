package com.ulacit.matriculas.matriculasulacit.Modelos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "Aula")
@EntityListeners(AuditingEntityListener.class)
public class Aula {

    private Integer idAula;
    private String tipo;
    private String area;
    private String numeroAula;

    public Aula() {
        super();
    }

    @Override
    public String toString() {
        return "Aula{" +
                "idAula=" + idAula +
                ", tipo='" + tipo + '\'' +
                ", area='" + area + '\'' +
                ", numeroAula='" + numeroAula + '\'' +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAula")
    public Integer getIdAula() {
        return idAula;
    }

    public void setIdAula(Integer idAula) {
        this.idAula = idAula;
    }

    @Column(name = "tipo")
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    @Column(name = "area")
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Column(name = "numeroAula")
    public String getNumeroAula() {
        return numeroAula;
    }

    public void setNumeroAula(String numeroAula) {
        this.numeroAula = numeroAula;
    }
}
