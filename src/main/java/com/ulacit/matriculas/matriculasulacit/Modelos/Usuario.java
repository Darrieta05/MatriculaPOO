package com.ulacit.matriculas.matriculasulacit.Modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "usuario")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true, allowSetters = true)

public class Usuario {
    
    private Integer idUsuario;
    private String Nombre;
    private String Clave;
    
    public Usuario()
    {
        super();
    }
    
    @Override
    public String toString() {
        return  "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + Nombre + '\'' +
                ", clave='" + Clave + '\'' +
                '}';
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    @Column(name = "nombre")
    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    @Column(name = "clave")
    public String getClave() {
        return Clave;
    }

    public void setClave(String Clave) {
        this.Clave = Clave;
    }
}
