package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno;
import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno_Id;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;


public interface AlumnoRepository extends JpaRepository<Alumno, Long> {
    List<Alumno> findByDeleted(Boolean deleted);
    Alumno findByIdAlumnoInAndDeletedIn(Alumno_Id idAlumno, Boolean deleted);
}
