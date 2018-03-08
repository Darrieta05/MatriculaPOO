package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Carrera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarreraRepository extends JpaRepository<Carrera, Integer>{
    List<Carrera> findByDeleted(Boolean deleted);
    Carrera findByCarreraIdInAndDeletedIn(int idCarrera, Boolean deleted);
}
