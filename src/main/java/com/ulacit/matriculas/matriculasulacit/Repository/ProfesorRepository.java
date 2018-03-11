package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProfesorRepository extends JpaRepository<Profesor, Integer> {
    List<Profesor> findByDeleted(Boolean deleted);
    Profesor findByIdProfesorInAndDeletedIn(int idProfesor, Boolean deleted);
}
