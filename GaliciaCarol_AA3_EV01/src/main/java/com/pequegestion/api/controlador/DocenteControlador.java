package com.pequegestion.api.controlador;

import com.pequegestion.api.modelo.Docente;
import com.pequegestion.api.repositorio.DocenteRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST del modulo Docente.
 * Expone el CRUD del recurso "docentes" apoyandose en DocenteRepositorio (Spring Data JPA).
 */
@RestController
@RequestMapping("/api/docentes")
public class DocenteControlador {

    private final DocenteRepositorio docenteRepositorio;

    public DocenteControlador(DocenteRepositorio docenteRepositorio) {
        this.docenteRepositorio = docenteRepositorio;
    }

    /** Crea un nuevo docente. POST /api/docentes */
    @PostMapping
    public Docente crear(@RequestBody Docente docente) {
        return docenteRepositorio.save(docente);
    }

    /** Lista todos los docentes. GET /api/docentes */
    @GetMapping
    public List<Docente> listar() {
        return docenteRepositorio.findAll();
    }

    /** Consulta un docente por id. GET /api/docentes/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<Docente> obtenerPorId(@PathVariable Long id) {
        return docenteRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Actualiza un docente existente. PUT /api/docentes/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<Docente> actualizar(@PathVariable Long id, @RequestBody Docente docenteActualizado) {
        return docenteRepositorio.findById(id)
                .map(docenteExistente -> {
                    docenteExistente.setNombre(docenteActualizado.getNombre());
                    docenteExistente.setEspecialidad(docenteActualizado.getEspecialidad());
                    return ResponseEntity.ok(docenteRepositorio.save(docenteExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /** Elimina un docente por id. DELETE /api/docentes/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!docenteRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        docenteRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
