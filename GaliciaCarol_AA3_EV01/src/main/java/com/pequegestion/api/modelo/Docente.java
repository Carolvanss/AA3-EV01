package com.pequegestion.api.modelo;

import jakarta.persistence.*;
import java.util.List;

/**
 * Entidad Docente: representa la tabla "docentes" en la base de datos "pequegestion".
 * Un docente puede tener a cargo varios grupos (relacion uno-a-muchos con Grupo).
 *
 * @Entity le indica a JPA/Hibernate que esta clase se puede guardar en la base de datos.
 * @Table indica explicitamente el nombre de la tabla asociada.
 */
@Entity
@Table(name = "docentes")
public class Docente {

    // Clave primaria: se genera automaticamente al insertar (no se asigna manualmente)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String especialidad; // ej: primera infancia, psicologia, pedagogia

    // Un docente puede estar asociado a varios grupos.
    // mappedBy = "docente" indica que la relacion la controla el atributo "docente" en la clase Grupo.
    @OneToMany(mappedBy = "docente")
    private List<Grupo> grupos;

    /** Constructor vacio requerido por JPA/Hibernate. */
    public Docente() {
    }

    public Docente(String nombre, String especialidad) {
        this.nombre = nombre;
        this.especialidad = especialidad;
    }

    // ----- Getters y setters (camelCase, segun estandar AA1-EV02) -----

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }
}
