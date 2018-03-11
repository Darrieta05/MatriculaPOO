package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Persona;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;

public interface PersonaRepository extends JpaRepository<Persona, Integer>{
    List<Persona> findByDeleted(Boolean deleted);
    Persona findByIdPersonaInAndDeletedIn(int idPersona, Boolean deleted);
}
