package com.pequegestion.api.controlador;

import com.pequegestion.api.modelo.Docente;
import com.pequegestion.api.repositorio.DocenteRepositorio;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST del modulo Docente.
 */
@RestController
@RequestMapping("/api/docentes")
public class DocenteControlador {

    private final DocenteRepositorio docenteRepositorio;

    public DocenteControlador(DocenteRepositorio docenteRepositorio) {
        this.docenteRepositorio = docenteRepositorio;
    }

    @PostMapping
    public Docente crear(@Valid @RequestBody Docente docente) {
        return docenteRepositorio.save(docente);
    }

    @GetMapping
    public List<Docente> listar() {
        return docenteRepositorio.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Docente> obtenerPorId(@PathVariable Long id) {
        return docenteRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Docente> actualizar(@PathVariable Long id, @Valid @RequestBody Docente docenteActualizado) {
        return docenteRepositorio.findById(id)
                .map(docenteExistente -> {
                    docenteExistente.setNombre(docenteActualizado.getNombre());
                    docenteExistente.setEspecialidad(docenteActualizado.getEspecialidad());
                    return ResponseEntity.ok(docenteRepositorio.save(docenteExistente));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!docenteRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        docenteRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}