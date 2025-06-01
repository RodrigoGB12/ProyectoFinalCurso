
# Saborify - Aplicación de Catering

## 📌 Descripción General

Saborify es una aplicación diseñada para gestionar un servicio de catering de forma eficiente. Permite administrar pedidos, artículos del menú, clientes, personal, alérgenos y mucho más. La solución se compone de un backend en Java con Spring Boot y un frontend desarrollado con Angular.

## 🎯 Objetivo de la Aplicación

El objetivo principal es proporcionar una herramienta intuitiva que permita a empresas de catering gestionar pedidos, menús y la asignación de personal, mejorando la eficiencia operativa y la satisfacción del cliente.

## 🧰 Tecnologías Usadas

- **Backend:** Java 17 con Spring Boot, Spring Data JPA, MySQL, Swagger para documentación de la API.
- **Frontend:** Angular 16, HTML, CSS, Bootstrap.
- **Base de Datos:** H2.
- **Herramientas de desarrollo:** IntelliJ IDEA, Visual Studio Code, Postman.

## 🏗️ Estructura del Proyecto

```
saborify/
├── backend/              # API REST con Spring Boot
│   └── src/
│       └── main/
│           ├── java/com/saborify
│           └── resources/
├── frontend/             # Aplicación Angular
│   └── src/
│       └── app/
└── README.md
```

## ⚙️ Manual de Instalación

### 1. Prerrequisitos

- Node.js (v18+ recomendado)
- Angular CLI (`npm install -g @angular/cli`)
- Java 17 o superior
- IntelliJ IDEA (para backend) y Visual Studio Code (para frontend)
- MySQL Server

### 2. Instalación del Backend

1. Clonar el repositorio y abrir la carpeta `backend` en IntelliJ.
3. Ejecutar la clase principal de Spring Boot (`SaborifyApplication.java`).
4. Acceder a la API vía Swagger en `http://localhost:8080/swagger-ui.html`.

### 3. Instalación del Frontend

1. Abrir la carpeta `frontend` en VS Code.
2. Ejecutar `npm install` para instalar dependencias.
3. Iniciar el servidor de desarrollo: `ng serve`.
4. Acceder a la app: `http://localhost:4200`.

## 🧪 Pruebas

- Pruebas unitarias en Angular.
- Pruebas de endpoints mediante Postman.
- Validaciones básicas en la UI.

## 🚧 Funcionalidades no implementadas

- Subida de imágenes para artículos del menú.
- Despliegue del proyecto en un entorno de producción.

## ❗ Dificultades Encontradas

- Problemas técnicos al subir y almacenar imágenes.
- Obstáculos en el despliegue final del proyecto en la nube.

## ✅ Valoración Final

El desarrollo de Saborify permitió aplicar conocimientos prácticos en tecnologías modernas como Angular y Spring Boot. Aunque algunas funcionalidades no pudieron completarse, la base del sistema es sólida y ampliable.

## 📦 Medios de Instalación

- Backend: Java 17 + Spring Boot + Maven
- Frontend: Node.js + Angular CLI
- IDEs recomendados: IntelliJ IDEA para backend, VS Code para frontend

## 📖 Licencia

Este proyecto es de uso educativo.
