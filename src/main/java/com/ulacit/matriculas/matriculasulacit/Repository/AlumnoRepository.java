package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlumnoRepository extends JpaRepository<Alumno, Integer>{
    List<Alumno> findByDeleted(Boolean deleted);
    Alumno findByAlumnoIdInAndDeletedIn(int alumnoId, Boolean deleted);
}
