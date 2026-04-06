
# Employees

Desarrollar un servicio backend resiliente, seguro y escalable que gestione operaciones sobre una entidad de "Empleado", siguiendo prácticas modernas de desarrollo, arquitectura de microservicios y aplicando los principios de DevSecOps.


## Requisitos
1. Tener instalado Docker
2. Una conexión a internet.
3. Crear un archivo .env con las variables de entorno

```
SPRING_PROFILES_ACTIVE=dev
BD_PASSWORD=cdas9238rncdsa!
BD_USERNAME=benjamin
BD_NAME=employees
```

## Instalación
1. Clonar el repositorio de GitHub
```
git clone https://github.com/benjadev7/employees-app.git employees-app
cd employees-app
```
2. crear imagen de Docker
```
docker compose up --build
```
3. ir a la siguiente URL para cargar datos necesarios de BD
```
http://localhost:8080/api/auth/seed
```
4. Login antes de cada request.
```
POST http://localhost:8080/api/auth/login

Body:
{
    "username": "admin",
    "password": "admin123"
}