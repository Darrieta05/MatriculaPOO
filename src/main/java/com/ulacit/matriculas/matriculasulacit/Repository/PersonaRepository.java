package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Persona;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<Persona, Integer> {
    List<Persona> findByEliminado(Boolean eliminado);
    Persona findByIdPersonaInAndEliminadoIn(int idPersona, Boolean eliminado);
}
