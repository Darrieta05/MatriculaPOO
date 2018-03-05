package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    List<Materia> findByDeleted(Boolean deleted);
    Materia findByMateriaIdInAndDeletedIn(int idDetalleMatricula, Boolean deleted);
}
