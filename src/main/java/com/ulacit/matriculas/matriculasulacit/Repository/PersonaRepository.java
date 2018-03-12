package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Long> {
    List<Persona> findByDeleted(Boolean deleted);
    Persona findByIdPersonaInAndDeletedIn(int idPersona, Boolean deleted);
}
