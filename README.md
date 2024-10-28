# Spaceship API

Spaceship API es una aplicación RESTful desarrollada en Spring Boot que permite gestionar naves espaciales de películas y series. 
Esta API permite realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) una vez autenticado con JWT.

Para el desarrollo de esta API se ha seguido el patrón de arquitectura hexagonal.

Adicionalmente se ha dockerizado para poder levantar instancias en local de Kafka y Redis.

## Tecnologías Usadas

    -> Java 21: Lenguaje de programación.
    -> Spring Boot: Framework principal para el desarrollo de la API.
    -> Docker: Para despliegue en contenedor.
    -> Docker-compose: Para despliegue de la APP junto a Kafka y Redis.
    -> Spring Security: Para la seguridad y autenticación mediante JWT.
    -> Swagger: Para la documentación y prueba de la API.
    -> Redis: Para almacenamiento en caché de las operaciones CRUD.
    -> Kafka: Para eventos asincrónicos (Configurado y con ejemplo).
    -> Liquibase: Para el control de versiones de la base de datos.
    -> H2 Database: Base de datos en memoria para pruebas y desarrollo.
    -> Spring Test: Testing unitario y de integración.
    -> Maven: Gestión de dependencias.
    -> Lombok: Ahorro de código biolerplate.

## Puesta en marcha de la APP
	1. Clonar el repositorio
	2. Lanzar el comando "docker-compose up --build" dentro del repositorio
	3. Acceder a la documentación de la API "http://localhost:8080/swagger-ui.html"

## Endpoints Principales
    Autenticación:
        POST /auth/login: Autenticación de usuarios y generación de JWT.
        
    Naves Espaciales:
        GET /api/v1/spaceship: Obtener todas las naves espaciales con paginación.
        GET /api/v1/spaceship/{id}: Obtener detalles de una nave espacial por ID.
        POST /api/v1/spaceship: Crear una nueva nave espacial.
        PUT /api/v1/spaceship/{id}: Actualizar una nave espacial existente.
        DELETE /api/v1/spaceship/{id}: Eliminar una nave espacial por ID.
        GET /api/v1/spaceship/name-search: Obtener todas las naves espaciales por incluir nombre
