package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Aula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AulaRepository extends JpaRepository<Aula, Integer> {
    List<Aula> findByDeleted(Boolean deleted);
    Aula findByIdAulaInAndDeletedIn(int idAula, Boolean deleted);
}
