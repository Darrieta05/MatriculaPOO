package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer> {
    List<Alumno> findByDeleted(Boolean deleted);
    Alumno findByIdAlumnoInAndDeletedIn(int idAlumno, Boolean deleted);
}
