package com.ulacit.matriculas.matriculasulacit.Modelos;

import javax.persistence.*;


@Entity
public class Usuario {
    
    private Integer idUsuario;
    private String Nombre;
    private String Clave;
    
    public Usuario()
    {
        super();
    }

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    

    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }
}
