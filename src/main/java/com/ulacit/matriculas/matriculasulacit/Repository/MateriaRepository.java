package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {
    List<Materia> findByDeleted(Boolean deleted);
    Materia findByMateriaIdInAndDeletedIn(int idDetalleMatricula, Boolean deleted);
}
