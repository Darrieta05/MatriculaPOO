package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Aula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AulaRepository extends JpaRepository<Aula, Integer>{
    List<Aula> findByDeleted(Boolean deleted);
    Aula findByAulaIdInAndDeletedIn(int idAula, Boolean deleted);
}
