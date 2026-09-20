package com.pequegestion.api.modelo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

/**
 * Entidad Nino: representa la tabla "ninos" en la base de datos "pequegestion".
 * Es la entidad principal de este modulo: cada nino pertenece a un grupo
 * y tiene asociado un acudiente responsable.
 *
 * Idea clave: el objeto Java Nino se convierte en un registro (fila) de la tabla "ninos".
 */
@Entity
@Table(name = "ninos")
public class Nino {

    // @Id -> clave primaria. @GeneratedValue -> la base de datos la asigna sola (no se escribe a mano).
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    private String fechaNacimiento; // formato yyyy-MM-dd

    @NotBlank(message = "El nombre del acudiente es obligatorio")
    private String nombreAcudiente; // referencia simple al acudiente responsable del nino

    // Relacion muchos-a-uno: varios ninos pertenecen al mismo grupo.
    @ManyToOne
    @JoinColumn(name = "grupo_id")
    @JsonIgnoreProperties("ninos") // evita el ciclo infinito Grupo -> Nino -> Grupo al serializar a JSON
    private Grupo grupo;

    /** Constructor vacio requerido por JPA/Hibernate. */
    public Nino() {
    }

    public Nino(String nombre, String fechaNacimiento, String nombreAcudiente, Grupo grupo) {
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
        this.nombreAcudiente = nombreAcudiente;
        this.grupo = grupo;
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

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getNombreAcudiente() {
        return nombreAcudiente;
    }

    public void setNombreAcudiente(String nombreAcudiente) {
        this.nombreAcudiente = nombreAcudiente;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }
}