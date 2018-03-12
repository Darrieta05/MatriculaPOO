package com.ulacit.matriculas.matriculasulacit.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import java.util.*;

@NoRepositoryBean
public interface PersonaBaseRepository<T> extends JpaRepository<T, Long> {
    List<T> findByDeleted(Boolean deleted);
    T findByIdInAndDeletedIn(int id, Boolean deleted);
}