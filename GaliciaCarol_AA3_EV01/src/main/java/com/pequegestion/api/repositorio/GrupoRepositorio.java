package com.pequegestion.api.repositorio;

import com.pequegestion.api.modelo.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio de la entidad Grupo.
 * Hereda el CRUD basico de JpaRepository y agrega una consulta propia.
 */
public interface GrupoRepositorio extends JpaRepository<Grupo, Long> {

    // Equivale a: SELECT * FROM grupos WHERE docente_id = ?
    List<Grupo> findByDocenteId(Long docenteId);
}
