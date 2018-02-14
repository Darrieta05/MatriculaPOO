package com.ulacit.matriculas.matriculasulacit.Modelos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Date;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "matricula")
@EntityListeners(AuditingEntityListener.class)
@JsonIgnoreProperties(allowGetters = true, allowSetters = true)

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
    
    @Override
    public String toString() {
        return "Matricula{" +
                "idMatricula=" + idMatricula +
                ", idUsuario='" + usuario + '\'' +
                ", idAlumno='" + alumno + '\'' +
                ", fecha='" + Fecha + '\'' +
                ", monto='" + Monto + '\'' +
                ", total='" + Total + '\'' +
                '}';
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
    
    @Column(name = "fecha")
    public Date getFecha() {
        return Fecha;
    }

    public void setFecha(Date Fecha) {
        this.Fecha = Fecha;
    }
    
    @Column(name = "monto")
    public Double getMonto() {
        return Monto;
    }

    public void setMonto(Double Monto) {
        this.Monto = Monto;
    }
    
    @Column(name = "total")
    public Double getTotal() {
        return Total;
    }

    public void setTotal(Double Total) {
        this.Total = Total;
    }
}
