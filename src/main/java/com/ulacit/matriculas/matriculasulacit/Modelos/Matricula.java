package com.ulacit.matriculas.matriculasulacit.Modelos;

import java.util.Date;
import javax.persistence.*;


@Entity
public class Matricula {

    private Integer idMatricula;
    private Usuario usuario;
    private Alumno alumno;
    private Date fecha;
    private Double monto;
    private Double total;

    /*Audit fields*/
    //@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date creationDate;
    //@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date updatedDate;
    private int createdBy;
    private int updatedBy;
    /*@ApiModelProperty(notes = "Indica si el registro se eliminó")*/
    private Boolean deleted = false;

    public Matricula() {
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


    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getMonto() {
        return monto;
    }

    public void setMonto(Double monto) {
        this.monto = monto;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    public int getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(int updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
