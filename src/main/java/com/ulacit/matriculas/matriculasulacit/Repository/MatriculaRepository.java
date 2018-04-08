package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    List<Matricula> findByEliminado(Boolean eliminado);
    Matricula findByIdMatriculaInAndEliminadoIn(int idMatricula, Boolean eliminado);
}
