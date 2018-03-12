package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Profesor;
import com.ulacit.matriculas.matriculasulacit.Modelos.Profesor_Id;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesorRepository extends JpaRepository<Profesor, Long> {
    List<Profesor> findByDeleted(Boolean deleted);
    Profesor findByIdProfesorInAndDeletedIn(Profesor_Id idProfesor, Boolean deleted);
}