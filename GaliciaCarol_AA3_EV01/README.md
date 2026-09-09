# GA7-220501096-AA3-EV01 — Codificación de módulos del software

**Aprendiz:** Carol Vanessa Galicia — Ficha 3235899
**Proyecto formativo:** PequeGestión (gestión de jardín infantil)

## 1. Framework aplicado
Se seleccionó y aplicó **Spring Boot + Spring Data JPA**, usando **Hibernate** como
implementación de JPA para mapear los objetos Java a la base de datos relacional
`pequegestion` (la misma diseñada en GA6-AA2-EV02/EV03).

## 2. Módulo codificado
Se codificó el módulo de **Niños, Grupos y Docentes**, tomando como base las entidades
definidas en el modelo entidad-relación (GA6-AA1-EV02): `NIÑO`, `GRUPO`, `DOCENTE`.

- `Nino` — pertenece a un `Grupo` (muchos a uno).
- `Grupo` — tiene un `Docente` a cargo y agrupa varios `Nino` (uno a muchos).
- `Docente` — puede tener a cargo varios `Grupo`.

Cada entidad tiene su repositorio (`JpaRepository`) que da el CRUD automático
(guardar, consultar, actualizar, eliminar) y su controlador REST correspondiente.

## 3. Herramienta de almacenamiento de datos integrada
Spring Data JPA + Hibernate + MySQL (`mysql-connector-j`). Hibernate traduce las
operaciones sobre los objetos Java a sentencias SQL sobre la base de datos `pequegestion`.

## 4. Estándar de codificación aplicado
Se aplicó el mismo estándar definido en **GA7-220501096-AA1-EV02**:
- **PascalCase** para nombres de clases (`Nino`, `Grupo`, `NinoControlador`, etc.).
- **camelCase** para nombres de métodos, atributos y variables
  (`getNombre`, `findByGrupoId`, `ninoRepositorio`, etc.).
- Comentarios (Javadoc y en línea) explicando el propósito de cada clase y método.

## 5. Estructura del proyecto
```
GaliciaCarol_AA3_EV01/
├── pom.xml
├── README.md
└── src/main/
    ├── java/com/pequegestion/api/
    │   ├── PequegestionApiApplication.java
    │   ├── modelo/         (entidades: Nino, Grupo, Docente)
    │   ├── repositorio/    (JpaRepository: NinoRepositorio, GrupoRepositorio, DocenteRepositorio)
    │   └── controlador/    (controladores REST con el CRUD)
    └── resources/
        └── application.properties  (conexión a MySQL "pequegestion")
```

## 6. Cómo ejecutar el proyecto
1. Abrir el proyecto en Eclipse o VS Code (como Maven Project).
2. En `application.properties`, poner la contraseña real de tu MySQL local.
3. Ejecutar `PequegestionApiApplication.java` (o `mvn spring-boot:run` desde la terminal).
4. Probar los endpoints, por ejemplo con Postman:
   - `POST /api/ninos` — crear un niño
   - `GET /api/ninos` — listar todos
   - `GET /api/ninos/grupo/{grupoId}` — niños de un grupo
   - `PUT /api/ninos/{id}` — actualizar
   - `DELETE /api/ninos/{id}` — eliminar
   - Igual para `/api/grupos` y `/api/docentes`.

## 7. Herramienta de versionamiento (creación del proyecto según AA1)
El proyecto se creó y versionó con Git/GitHub, tal como se configuró en GA7-AA1-EV04/EV05:

```bash
cd GaliciaCarol_AA3_EV01
git init
git add .
git commit -m "Codificacion de modulos Nino, Grupo y Docente con Spring Boot y Hibernate"
git remote add origin https://github.com/Carolvanss/NOMBRE_DEL_REPO.git
git branch -M main
git push -u origin main
```

> Si prefieres usar el mismo repositorio `Programa-Git` que ya tienes conectado, crea
> una carpeta o rama nueva dentro de él para este módulo en vez de un repo aparte.
