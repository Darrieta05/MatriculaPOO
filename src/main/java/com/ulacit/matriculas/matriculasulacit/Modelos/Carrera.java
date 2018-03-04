package com.ulacit.matriculas.matriculasulacit.Modelos;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Entity
@Table(name = "Carrera")
@EntityListeners(AuditingEntityListener.class)
public class Carrera {

    private Integer idCarrera;
    private String codigo;
    private String nombre;
    private Integer totalCreditos;

    public Carrera() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCarrera")
    public Integer getIdCarrera() {
        return idCarrera;
    }
    public void setIdCarrera(Integer idCarrera) {
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
