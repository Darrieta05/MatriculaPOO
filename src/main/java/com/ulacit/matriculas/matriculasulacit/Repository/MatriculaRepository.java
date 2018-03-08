package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer>{
    List<Matricula> findByDeleted(Boolean deleted);
    Matricula findByMatriculaIdInAndDeletedIn(int idMatricula, Boolean deleted);
}
