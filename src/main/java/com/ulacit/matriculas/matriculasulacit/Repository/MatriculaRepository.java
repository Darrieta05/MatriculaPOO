package com.ulacit.matriculas.matriculasulacit.Repository;

import com.ulacit.matriculas.matriculasulacit.Modelos.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MatriculaRepository extends JpaRepository<Matricula, Integer> {
    /*Aqui se aplica un ejemplo del patron de Adapter, no se crea el query de SQL
    Sino mas bien, JPA brinda esas funcionalidades de forma interna, con palabras reservadas*/
    List<Matricula> findByEliminado(Boolean eliminado);

    Matricula findByIdMatriculaInAndEliminadoIn(int idMatricula, Boolean eliminado);
}
