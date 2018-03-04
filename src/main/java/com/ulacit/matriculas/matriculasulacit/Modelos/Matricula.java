package com.ulacit.matriculas.matriculasulacit.Modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class Matricula {
    
    private Integer idMatricula;
    private Usuario usuario;
    private Alumno alumno;
    private Date Fecha;
    private Double Monto;
    private Double Total;
    
    public Matricula()
    {
        super();
    }

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdMatricula() {
        return idMatricula;
    }
    
    public void setIdMatricula(Integer idMatricula) {
        this.idMatricula = idMatricula;
    }
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idUsuario")
    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "idAlumno")
    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }

    public Double getMonto() {
        return Monto;
    }

    public void setMonto(Double Monto) {
        this.Monto = Monto;
    }

    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }
}
