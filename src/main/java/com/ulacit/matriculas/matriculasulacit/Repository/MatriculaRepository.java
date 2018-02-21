package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository extends JpaRepository<Matricula, Integer>, JpaSpecificationExecutor<Matricula> {
}
