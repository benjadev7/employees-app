
# Employees

Desarrollar un servicio backend resiliente, seguro y escalable que gestione operaciones sobre una entidad de "Empleado", siguiendo prácticas modernas de desarrollo, arquitectura de microservicios y aplicando los principios de DevSecOps.

## 2. Características
Aquí tienes una lista de lo que incluye:
* **Fácil de usar:** Configuración rápida.
* **Ligero:** No consume casi recursos.
* **Código abierto:** Puedes contribuir cuando quieras.

## 3. Requisitos
1. Tener instalado Docker
2. Una conexión a internet.
3. Crear un archivo .env con las variables de entorno

SPRING_PROFILES_ACTIVE=dev

BD_PASSWORD=cdas9238rncdsa!
BD_USERNAME=benjamin
BD_NAME=employees

## 4. Instalación
```bash
git clone [https://github.com/benjadev7/employees-app.git](https://github.com/benjadev7/employees-app.git)
cd proyecto
docker compose up --build
ir a la siguiente URL para cargar datos necesarios de BD
http://localhost:8080/api/auth/seed