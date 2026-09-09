package com.pequegestion.api.repositorio;

import com.pequegestion.api.modelo.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de la entidad Docente.
 * Con solo heredar de JpaRepository<Docente, Long> ya contamos con el CRUD completo:
 * save(), findAll(), findById(), deleteById(), etc.
 */
public interface DocenteRepositorio extends JpaRepository<Docente, Long> {
}
