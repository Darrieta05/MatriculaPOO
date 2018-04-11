package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Profesor;
import com.ulacit.matriculas.matriculasulacit.Modelos.Profesor_Id;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Profesor_Id> {
}