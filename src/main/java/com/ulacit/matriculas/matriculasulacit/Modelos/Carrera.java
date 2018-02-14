package com.ulacit.matriculas.matriculasulacit.Modelos;

public class Carrera {

    private Integer idCarrera;
    private String codigo;
    private String nombre;
    private Integer totalCreditos;

    public Carrera() {
        super();
    }

    @Override
    public String toString() {
        return "Carrera{" +
                "idCarrera=" + idCarrera +
                ", codigo='" + codigo + '\'' +
                ", nombre='" + nombre + '\'' +
                ", totalCreditos=" + totalCreditos +
                '}';
    }

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
