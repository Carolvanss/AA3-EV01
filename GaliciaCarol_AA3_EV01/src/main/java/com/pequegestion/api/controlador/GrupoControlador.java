package com.pequegestion.api.controlador;

import com.pequegestion.api.modelo.Grupo;
import com.pequegestion.api.repositorio.GrupoRepositorio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST del modulo Grupo.
 */
@RestController
@RequestMapping("/api/grupos")
public class GrupoControlador {

    private final GrupoRepositorio grupoRepositorio;

    public GrupoControlador(GrupoRepositorio grupoRepositorio) {
        this.grupoRepositorio = grupoRepositorio;
    }

    @PostMapping
    public Grupo crear(@Valid @RequestBody Grupo grupo) {
        return grupoRepositorio.save(grupo);
    }

    @GetMapping
    public List<Grupo> listar() {
        return grupoRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Grupo> obtenerPorId(@PathVariable Long id) {
        return grupoRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/docente/{docenteId}")
    public List<Grupo> listarPorDocente(@PathVariable Long docenteId) {
        return grupoRepositorio.findByDocenteId(docenteId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Grupo> actualizar(@PathVariable Long id, @Valid @RequestBody Grupo grupoActualizado) {
        return grupoRepositorio.findById(id)
                .map(grupoExistente -> {
                    grupoExistente.setNombre(grupoActualizado.getNombre());
                    grupoExistente.setDocente(grupoActualizado.getDocente());
                    return ResponseEntity.ok(grupoRepositorio.save(grupoExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!grupoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        grupoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}