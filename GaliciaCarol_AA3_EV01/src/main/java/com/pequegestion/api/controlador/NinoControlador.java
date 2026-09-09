package com.pequegestion.api.controlador;

import com.pequegestion.api.modelo.Nino;
import com.pequegestion.api.repositorio.NinoRepositorio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controlador REST del modulo Nino.
 * Expone las operaciones CRUD del recurso "ninos" como una API web,
 * recibiendo el repositorio por inyeccion de dependencias (DI) en el constructor,
 * en vez de crearlo con "new".
 *
 * Nombres en camelCase para metodos y variables, PascalCase para la clase
 * (mismo estandar definido en AA1-EV02).
 */
@RestController
@RequestMapping("/api/ninos")
public class NinoControlador {

    private final NinoRepositorio ninoRepositorio;

    // Inyeccion de dependencias por constructor
    public NinoControlador(NinoRepositorio ninoRepositorio) {
        this.ninoRepositorio = ninoRepositorio;
    }

    /** Crea un nuevo niño. POST /api/ninos */
    @PostMapping
    public Nino crear(@RequestBody Nino nino) {
        return ninoRepositorio.save(nino);
    }

    /** Lista todos los niños registrados. GET /api/ninos */
    @GetMapping
    public List<Nino> listar() {
        return ninoRepositorio.findAll();
    }

    /** Consulta un niño por su id. GET /api/ninos/{id} */
    @GetMapping("/{id}")
    public ResponseEntity<Nino> obtenerPorId(@PathVariable Long id) {
        return ninoRepositorio.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /** Lista los niños de un grupo especifico. GET /api/ninos/grupo/{grupoId} */
    @GetMapping("/grupo/{grupoId}")
    public List<Nino> listarPorGrupo(@PathVariable Long grupoId) {
        return ninoRepositorio.findByGrupoId(grupoId);
    }

    /** Busca niños cuyo nombre contenga el texto indicado. GET /api/ninos/buscar?nombre=... */
    @GetMapping("/buscar")
    public List<Nino> buscarPorNombre(@RequestParam String nombre) {
        return ninoRepositorio.findByNombreContainingIgnoreCase(nombre);
    }

    /** Actualiza los datos de un niño existente. PUT /api/ninos/{id} */
    @PutMapping("/{id}")
    public ResponseEntity<Nino> actualizar(@PathVariable Long id, @RequestBody Nino ninoActualizado) {
        return ninoRepositorio.findById(id)
                .map(ninoExistente -> {
                    ninoExistente.setNombre(ninoActualizado.getNombre());
                    ninoExistente.setFechaNacimiento(ninoActualizado.getFechaNacimiento());
                    ninoExistente.setNombreAcudiente(ninoActualizado.getNombreAcudiente());
                    ninoExistente.setGrupo(ninoActualizado.getGrupo());
                    Nino ninoGuardado = ninoRepositorio.save(ninoExistente);
                    return ResponseEntity.ok(ninoGuardado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    /** Elimina un niño por su id. DELETE /api/ninos/{id} */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        if (!ninoRepositorio.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        ninoRepositorio.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
