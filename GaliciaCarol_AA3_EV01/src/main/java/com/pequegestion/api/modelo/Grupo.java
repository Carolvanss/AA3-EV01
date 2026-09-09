package com.pequegestion.api.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.util.List;

/**
 * Entidad Grupo: representa la tabla "grupos" en la base de datos "pequegestion".
 * Cada grupo tiene un docente a cargo (muchos grupos -> un docente)
 * y agrupa a varios niños (un grupo -> muchos niños).
 */
@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre; // ej: "Sala Cuna", "Parvulos A"

    // Relacion muchos-a-uno: varios grupos pueden tener el mismo docente.
    // @JsonIgnoreProperties evita bucles infinitos al convertir a JSON (Docente -> Grupo -> Docente...)
    @ManyToOne
    @JoinColumn(name = "docente_id")
    @JsonIgnoreProperties("grupos")
    private Docente docente;

    // Relacion uno-a-muchos: un grupo contiene varios niños.
    @OneToMany(mappedBy = "grupo")
    @JsonIgnoreProperties("grupo")
    private List<Nino> ninos;

    /** Constructor vacio requerido por JPA/Hibernate. */
    public Grupo() {
    }

    public Grupo(String nombre, Docente docente) {
        this.nombre = nombre;
        this.docente = docente;
    }

    // ----- Getters y setters -----

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

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public List<Nino> getNinos() {
        return ninos;
    }

    public void setNinos(List<Nino> ninos) {
        this.ninos = ninos;
    }
}
