package com.pequegestion.api.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import java.util.List;

/**
 * Entidad Grupo: representa la tabla "grupos" en la base de datos "pequegestion".
 * Cada grupo tiene un docente a cargo (muchos grupos -> un docente)
 * y agrupa a varios ninos (un grupo -> muchos ninos).
 */
@Entity
@Table(name = "grupos")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre del grupo es obligatorio")
    private String nombre; // ej: "Sala Cuna", "Parvulos A"

    @ManyToOne
    @JoinColumn(name = "docente_id")
    @JsonIgnoreProperties("grupos")
    private Docente docente;

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