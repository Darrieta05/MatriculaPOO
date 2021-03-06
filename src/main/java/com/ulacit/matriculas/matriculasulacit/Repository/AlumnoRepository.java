package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno;
import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno_Id;
import com.ulacit.matriculas.matriculasulacit.Modelos.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/*Aqui se aplica el patron Iterator en el list<> y Repository*/
public interface AlumnoRepository extends JpaRepository<Alumno, Alumno_Id> {
    List<Alumno> findByEliminado(Boolean eliminado);
}
