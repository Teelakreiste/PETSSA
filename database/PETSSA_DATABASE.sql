

-- Si existe la base de datos, se elimina
DROP DATABASE IF EXISTS db_petssa;

-- Se crea la base de datos
CREATE DATABASE db_petssa;

-- Selección de la base de datos
USE db_petssa;

-- Drop de las tablas
DROP TABLE IF EXISTS tdetalles;
DROP TABLE IF EXISTS tmedicamentos;
DROP TABLE IF EXISTS tmascotas;
DROP TABLE IF EXISTS tclientes;

-- Creación de las tablas

-- Tabla de clientes
CREATE TABLE tclientes (
    identificacion INT AUTO_INCREMENT NOT NULL,
    cedula VARCHAR(15) NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    segundo_nombre VARCHAR(20),
    apellido_paterno VARCHAR(25) NOT NULL,
    apellido_materno VARCHAR(25) NOT NULL,
    direccion VARCHAR(250) NOT NULL,
    telefono VARCHAR(10) NOT NULL,
    PRIMARY KEY (identificacion), -- Añade un índice a la columna identificacion
    UNIQUE KEY (cedula) -- Añade un índice a la columna cedula
);

-- Tabla de mascotas
CREATE TABLE tmascotas (
    identificacion INT AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(15) NOT NULL,
    raza VARCHAR(30) NOT NULL,
    edad INT NOT NULL,
    peso DECIMAL(5, 2) NOT NULL,
    tclientes_cedula VARCHAR(15) NOT NULL,
    tclientes_identificacion INT NOT NULL,
    PRIMARY KEY (identificacion),
    FOREIGN KEY (tclientes_cedula, tclientes_identificacion) REFERENCES tclientes(cedula, identificacion)
);

-- Tabla de medicamentos
CREATE TABLE tmedicamentos (
    identificacion INT AUTO_INCREMENT NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    descripcion VARCHAR(350) NOT NULL,
    PRIMARY KEY (identificacion)
);

-- Tabla de detalles de tratamiento
CREATE TABLE tdetalles (
    registro INT AUTO_INCREMENT NOT NULL,
    tmascotas_identificacion INT NOT NULL,
    tmedicamentos_identificacion INT NOT NULL,
    dosis VARCHAR(50) NOT NULL,
    PRIMARY KEY (registro),
    FOREIGN KEY (tmascotas_identificacion) REFERENCES tmascotas(identificacion),
    FOREIGN KEY (tmedicamentos_identificacion) REFERENCES tmedicamentos(identificacion)
);

-- Triggers (disparadores)

-- Comprobar que el usuario no exista en la tabla de clientes antes de insertarlo (trigger)
DELIMITER $$
CREATE TRIGGER tclientes_before_insert BEFORE INSERT ON tclientes
FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM tclientes WHERE cedula = NEW.cedula) > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'El cliente ya existe';
    END IF;
END $$
DELIMITER ;

-- Comprobar que la mascota no exista en la tabla de mascotas antes de insertarla (trigger)
DELIMITER $$
CREATE TRIGGER tmascotas_before_insert BEFORE INSERT ON tmascotas
FOR EACH ROW
BEGIN
    IF (SELECT COUNT(*) FROM tmascotas WHERE nombre = NEW.nombre AND tclientes_cedula = NEW.tclientes_cedula AND tclientes_identificacion = NEW.tclientes_identificacion) > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'La mascota ya existe';
    END IF;
END $$
DELIMITER ;

-- Procedimientos almacenados (stored procedures)
-- Insertar o actualizar un detalle de tratamiento
DELIMITER //
CREATE PROCEDURE InsertarActualizarDetalle(
  IN p_mascota_identificacion INT,
  IN p_medicamento_identificacion INT,
  IN p_dosis VARCHAR(50)
)
BEGIN
  DECLARE detalle_existente INT;

  -- Verificar si el detalle ya existe
  SELECT COUNT(*) INTO detalle_existente
  FROM tdetalles
  WHERE tmascotas_identificacion = p_mascota_identificacion
  AND tmedicamentos_identificacion = p_medicamento_identificacion;

  -- Si el detalle no existe, insertarlo
  IF detalle_existente = 0 THEN
    INSERT INTO tdetalles (tmascotas_identificacion, tmedicamentos_identificacion, dosis)
    VALUES (p_mascota_identificacion, p_medicamento_identificacion, p_dosis);
  ELSE
    -- Si el detalle ya existe, actualizar la dosis
    UPDATE tdetalles
    SET dosis = p_dosis
    WHERE tmascotas_identificacion = p_mascota_identificacion
    AND tmedicamentos_identificacion = p_medicamento_identificacion;
  END IF;
END;
//
DELIMITER ;

-- Eliminar mascota y sus detalles
DELIMITER //
CREATE PROCEDURE EliminarMascotaYDetalles(IN p_mascota_identificacion INT)
BEGIN
  -- Eliminar detalles de la mascota
  DELETE FROM tdetalles WHERE tmascotas_identificacion = p_mascota_identificacion;

  -- Eliminar la mascota
  DELETE FROM tmascotas WHERE identificacion = p_mascota_identificacion;
END;
//
DELIMITER ;


-- Inserción de datos de prueba

-- Insertar clientes
INSERT INTO tclientes (cedula, nombre, segundo_nombre, apellido_paterno, apellido_materno, direccion, telefono)
VALUES
  ('123456789', 'Juan', 'Carlos', 'Pérez', 'López', 'Calle 123', '555-1234'),
  ('987654321', 'María', 'Isabel', 'González', 'Martínez', 'Avenida 456', '555-9876'),
  ('111111111', 'Pedro', NULL, 'Sánchez', 'Ramírez', 'Carrera 789', '555-1111');

-- Insertar medicamentos
INSERT INTO tmedicamentos (nombre, descripcion)
VALUES
  ('Paracetamol', 'Analgésico y antipirético común'),
  ('Amoxicilina', 'Antibiótico para infecciones bacterianas'),
  ('Ibuprofeno', 'Antiinflamatorio y analgésico');

-- Insertar mascotas
INSERT INTO tmascotas (nombre, raza, edad, peso, tclientes_cedula, tclientes_identificacion)
VALUES
  ('Fido', 'Labrador', 3, 25.5, '123456789', 1),
  ('Luna', 'Siamesa', 2, 7.8, '987654321', 2),
  ('Max', 'Dálmata', 1, 20.0, '111111111', 3);

-- Insertar detalles de tratamiento
INSERT INTO tdetalles (tmascotas_identificacion, tmedicamentos_identificacion, dosis)
VALUES
  (1, 1, '1 tableta cada 8 horas'),
  (2, 2, '5 ml cada 12 horas'),
  (3, 3, '2 tabletas cada 6 horas');


INSERT INTO tdetalles (tmascotas_identificacion, tmedicamentos_identificacion, dosis)
VALUES
  (1, 3, '1/2 de tableta cada 24 horas');
