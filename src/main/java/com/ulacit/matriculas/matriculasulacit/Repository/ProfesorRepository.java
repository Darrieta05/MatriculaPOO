package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Profesor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfesorRepository extends JpaRepository<Profesor, Integer>{
    List<Profesor> findByDeleted(Boolean deleted);
    Profesor findByProfesorIdInAndDeletedIn(int idProfesor, Boolean deleted);
}
