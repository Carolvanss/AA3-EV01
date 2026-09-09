package com.pequegestion.api.controlador;

import com.pequegestion.api.modelo.Grupo;
import com.pequegestion.api.repositorio.GrupoRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST del modulo Grupo.
 * Expone el CRUD del recurso "grupos" apoyandose en GrupoRepositorio (Spring Data JPA).
 */
@RestController
@RequestMapping("/api/grupos")
public class GrupoControlador {

    private final GrupoRepositorio grupoRepositorio;

    public GrupoControlador(GrupoRepositorio grupoRepositorio) {
        this.grupoRepositorio = grupoRepositorio;
    }

    /** Crea un nuevo grupo. POST /api/grupos */
    @PostMapping
    public Grupo crear(@RequestBody Grupo grupo) {
        return grupoRepositorio.save(grupo);
    }

    /** Lista todos los grupos. GET /api/grupos */
    @GetMapping
    public List<Grupo> listar() {
        return grupoRepositorio.findAll();
    }

    /** Consulta un grupo por id. GET /api/grupos/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<Grupo> obtenerPorId(@PathVariable Long id) {
        return grupoRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Lista los grupos a cargo de un docente. GET /api/grupos/docente/{docenteId} */
    @GetMapping("/docente/{docenteId}")
    public List<Grupo> listarPorDocente(@PathVariable Long docenteId) {
        return grupoRepositorio.findByDocenteId(docenteId);
    }

    /** Actualiza un grupo existente. PUT /api/grupos/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<Grupo> actualizar(@PathVariable Long id, @RequestBody Grupo grupoActualizado) {
        return grupoRepositorio.findById(id)
                .map(grupoExistente -> {
                    grupoExistente.setNombre(grupoActualizado.getNombre());
                    grupoExistente.setDocente(grupoActualizado.getDocente());
                    return ResponseEntity.ok(grupoRepositorio.save(grupoExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /** Elimina un grupo por id. DELETE /api/grupos/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!grupoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        grupoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
