-- EjPROut7_Script:
DROP DATABASE IF EXISTS `EjPROut7`;

CREATE DATABASE EjPROut7 CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `EjPROut7`;

CREATE TABLE Roles (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    fecha_actualizacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE Productos (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    descripcion TEXT,
    precio DECIMAL(10, 2)
);

CREATE TABLE Usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50),
    email VARCHAR(50),
    token VARCHAR(50),
    rol_id INT,
    FOREIGN KEY (rol_id) REFERENCES Roles(id)
);

CREATE TABLE Orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    producto_id INT,
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id),
    FOREIGN KEY (producto_id) REFERENCES Productos(id)
);

CREATE TABLE Comentarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    usuario_id INT,
    producto_id INT,
    comentario TEXT,
    FOREIGN KEY (usuario_id) REFERENCES Usuarios(id),
    FOREIGN KEY (producto_id) REFERENCES Productos(id)
);

-- Insertar Datos
-- Insertar Roles
INSERT INTO Roles (nombre) VALUES
    ('Administrador'),
    ('Editor'),
    ('Usuario Registrado'),
    ('Invitado');

-- Insertar 100 usuarios
INSERT INTO Usuarios (nombre, email, token, rol_id)
SELECT CONCAT('Usuario', id), CONCAT('usuario', id, '@example.com'), SUBSTRING(MD5(RAND()), 1, 16), MOD(id, 4) + 1
FROM (
  SELECT @row := @row + 1 AS id
  FROM (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) t1,
       (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) t2,
       (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) t3,
       (SELECT 0 UNION ALL SELECT 1 UNION ALL SELECT 2 UNION ALL SELECT 3 UNION ALL SELECT 4) t4,
       (SELECT @row := 0) t5
  LIMIT 100
) t6;

-- Crear 30 productos
INSERT INTO Productos (nombre, descripcion, precio) VALUES
    ('Producto 1', 'Descripción del Producto 1', 10.99),
    ('Producto 2', 'Descripción del Producto 2', 15.99),
    ('Producto 3', 'Descripción del Producto 3', 8.99),
    ('Producto 4', 'Descripción del Producto 4', 12.99),
    ('Producto 5', 'Descripción del Producto 5', 9.99),
    ('Producto 6', 'Descripción del Producto 6', 14.99),
    ('Producto 7', 'Descripción del Producto 7', 11.99),
    ('Producto 8', 'Descripción del Producto 8', 7.99),
    ('Producto 9', 'Descripción del Producto 9', 16.99),
    ('Producto 10', 'Descripción del Producto 10', 13.99),
    ('Producto 11', 'Descripción del Producto 11', 6.99),
    ('Producto 12', 'Descripción del Producto 12', 17.99),
    ('Producto 13', 'Descripción del Producto 13', 10.49),
    ('Producto 14', 'Descripción del Producto 14', 14.49),
    ('Producto 15', 'Descripción del Producto 15', 8.49),
    ('Producto 16', 'Descripción del Producto 16', 12.49),
    ('Producto 17', 'Descripción del Producto 17', 9.49),
    ('Producto 18', 'Descripción del Producto 18', 13.49),
    ('Producto 19', 'Descripción del Producto 19', 7.49),
    ('Producto 20', 'Descripción del Producto 20', 15.49),
    ('Producto 21', 'Descripción del Producto 21', 19.99),
    ('Producto 22', 'Descripción del Producto 22', 29.99),
    ('Producto 23', 'Descripción del Producto 23', 9.99),
    ('Producto 24', 'Descripción del Producto 24', 14.99),
    ('Producto 25', 'Descripción del Producto 25', 39.99),
    ('Producto 26', 'Descripción del Producto 26', 24.99),
    ('Producto 27', 'Descripción del Producto 27', 49.99),
    ('Producto 28', 'Descripción del Producto 28', 12.99),
    ('Producto 29', 'Descripción del Producto 29', 34.99),
    ('Producto 30', 'Descripción del Producto 30', 17.99);

-- Insertar entre 0 y 5 pedidos por usuario
INSERT INTO Orders (usuario_id, producto_id)
SELECT Usuarios.id, Productos.id
FROM Usuarios, Productos
WHERE RAND() < 0.5  -- Probabilidad del 50% de que se genere un pedido por usuario
    AND (SELECT COUNT(*) FROM Orders WHERE usuario_id = Usuarios.id) < 5  -- Máximo de 5 pedidos por usuario
ORDER BY RAND();


-- Insertar entre 0 y 3 comentarios por usuario
INSERT INTO Comentarios (usuario_id, producto_id, comentario)
SELECT Usuarios.id, Productos.id, CONCAT('Comentario de usuario ', Usuarios.id, ' sobre producto ', Productos.id)
FROM Usuarios, Productos
WHERE RAND() < 0.3  -- Probabilidad del 30% de que se genere un comentario por usuario
    AND (SELECT COUNT(*) FROM Comentarios WHERE usuario_id = Usuarios.id) < 3  -- Máximo de 3 comentarios por usuario
ORDER BY RAND();
