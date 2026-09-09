package com.pequegestion.api.repositorio;

import com.pequegestion.api.modelo.Nino;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 * Repositorio de la entidad Nino.
 * JpaRepository<Nino, Long> recibe dos parametros:
 *   - Nino: la entidad que administra este repositorio.
 *   - Long: el tipo de dato de la clave primaria (id).
 *
 * Al extender JpaRepository, Spring Data JPA ya nos da el CRUD basico en tiempo
 * de ejecucion (save, findAll, findById, deleteById, etc.), apoyandose en Hibernate.
 * No se escribe SQL manualmente para estas operaciones.
 */
public interface NinoRepositorio extends JpaRepository<Nino, Long> {

    // Consulta "magica": Spring Data JPA interpreta el nombre del metodo y genera
    // el SQL equivalente a: SELECT * FROM ninos WHERE grupo_id = ?
    List<Nino> findByGrupoId(Long grupoId);

    // Equivale a: SELECT * FROM ninos WHERE nombre LIKE %valor% (sin distinguir mayus/minus)
    List<Nino> findByNombreContainingIgnoreCase(String nombre);
}
