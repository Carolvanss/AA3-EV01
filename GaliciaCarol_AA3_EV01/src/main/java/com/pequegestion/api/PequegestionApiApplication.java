package com.pequegestion.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Clase principal del modulo.
 * Al ejecutarla, Spring Boot levanta un servidor Tomcat embebido y expone
 * los controladores REST de Nino, Grupo y Docente sobre esa base de datos.
 *
 * Estandar de codificacion aplicado (definido en GA7-220501096-AA1-EV02):
 * - PascalCase para nombres de clases (ej: PequegestionApiApplication).
 * - camelCase para nombres de metodos y variables.
 */
@SpringBootApplication
public class PequegestionApiApplication {

    public static void main(String[] args) {
        // Punto de entrada de la aplicacion
        SpringApplication.run(PequegestionApiApplication.class, args);
    }
}
