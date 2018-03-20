package com.ulacit.matriculas.matriculasulacit.Modelos;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
public class Alumno implements Serializable{
    
    @EmbeddedId
    private Alumno_Id idAlumno;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="idCarrera")
    private Carrera carrera;
    private String Beca;


    /*Audit fields*/
    //@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date creationDate;
    //@JsonFormat(pattern="yyyy-MM-dd@HH:mm:ss.SSSZ")
    private Date updatedDate;
    private int createdBy;
    private int updatedBy;
    /*@ApiModelProperty(notes = "Indica si el registro se eliminó")*/
    private Boolean deleted = false;


    public Alumno() {
        super();
    }

    public Alumno_Id getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Alumno_Id idAlumno) {
        this.idAlumno = idAlumno;
    }
    
    public Carrera getCarrera() {
        return carrera;
    }

    public void setCarrera(Carrera carrera) {
        this.carrera = carrera;
    }

    public String getBeca() {
        return Beca;
    }

    public void setBeca(String beca) {
        Beca = beca;
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
