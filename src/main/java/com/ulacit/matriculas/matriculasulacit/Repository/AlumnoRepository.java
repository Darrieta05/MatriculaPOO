package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno;
import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno_Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoRepository extends JpaRepository<Alumno, Alumno_Id> {
}
