package com.ulacit.matriculas.matriculasulacit.Modelos;

import javax.persistence.*;


@Entity
public abstract class Persona {

    private Integer idPersona;
    private Integer cedula;
    private String nombre;
    private String apellido;
    private Integer edad;
    private String sexo;


    public Persona() {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdPersona() {
        return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
        this.idPersona = idPersona;
    }

    public Integer getCedula() {
        return cedula;
    }

    public void setCedula(Integer cedula) {
        this.cedula = cedula;
    }


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }


    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }


    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
