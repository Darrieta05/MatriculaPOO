package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.DetalleMatricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetalleMatriculaRepository extends JpaRepository<DetalleMatricula, Integer> {
    List<DetalleMatricula> findByDeleted(Boolean deleted);
    DetalleMatricula findByDetalleMatriculaIdInAndDeletedIn(int idDetalleMatricula, Boolean deleted);
}
