package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Alumno;
import com.ulacit.matriculas.matriculasulacit.Modelos.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactoRepository extends JpaRepository<Contacto, Integer>{
    List<Contacto> findByDeleted(Boolean deleted);
    Contacto findByContactoIdInAndDeletedIn(int idContacto, Boolean deleted);
}
