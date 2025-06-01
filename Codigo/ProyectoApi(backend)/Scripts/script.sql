DROP DATABASE IF EXISTS gestionCatering;

CREATE DATABASE gestionCatering;
USE gestionCatering;

CREATE TABLE clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    correo_electronico VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(15),
    direccion VARCHAR(255),
    dni VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE personal (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    rol ENUM('Cocinero', 'Conductor') NOT NULL,
    correo_electronico VARCHAR(100) UNIQUE NOT NULL,
    telefono VARCHAR(15),
    dni VARCHAR(20) UNIQUE NOT NULL
);

CREATE TABLE alergenos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255)
);

CREATE TABLE articulos_menu (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(255),
    precio DECIMAL(10, 2) NOT NULL
);

CREATE TABLE pedidos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    cliente_id BIGINT NOT NULL,
    fecha_pedido TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    estado VARCHAR(50) NOT NULL,
    monto_total DECIMAL(10, 2) NOT NULL,
    personal_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id),
    FOREIGN KEY (personal_id) REFERENCES personal(id)
);

CREATE TABLE articulos_pedido (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id BIGINT NOT NULL,
    articulo_menu_id BIGINT NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    personal_id BIGINT,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (articulo_menu_id) REFERENCES articulos_menu(id),
    FOREIGN KEY (personal_id) REFERENCES personal(id)
);

CREATE TABLE articulos_menu_alergenos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    articulo_menu_id BIGINT NOT NULL,
    alergeno_id BIGINT NOT NULL,
    FOREIGN KEY (articulo_menu_id) REFERENCES articulos_menu(id),
    FOREIGN KEY (alergeno_id) REFERENCES alergenos(id)
);