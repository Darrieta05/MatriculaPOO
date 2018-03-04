package com.ulacit.matriculas.matriculasulacit.Modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Contacto {

    private Integer idContacto;
    private String nombre;
    private String descripcion;
    private Persona persona;
    private String tipo;


    public Contacto()
    {
        super();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdContacto() {
        return idContacto;
    }

    public void setIdContacto(Integer idContacto) {
        this.idContacto = idContacto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idPersona")
    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
