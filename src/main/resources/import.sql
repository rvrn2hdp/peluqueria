-- Active: 1702505723442@@127.0.0.1@3306@peluqueria_db

-- Ingresar Roles para los usuarios:

INSERT INTO rol (descripcion) VALUES ('ROLE_USUARIO');

INSERT INTO rol (descripcion) VALUES ('ROLE_ESTILISTA');

INSERT INTO rol (descripcion) VALUES ('ROLE_ADMINISTRADOR');

-- Agregar Usuarios:

-- INSERT INTO usuario (activo, id_rol, clave, direccion, email, genero, nombre, notas, telefono) VALUES ('1', '1', 'clave1', 'Calle 1 # 2-3', 'pIiQo@example.com', 'M', 'Jorge', 'Pepito Sobrino', '123456789');
-- INSERT INTO usuario (activo, id_rol, clave, direccion, email, genero, nombre, notas, telefono) VALUES ('1', '1', 'clave2','Calle 23 # 1-7', 'pIs2s3o@example.com', 'F', 'Jorgelina', 'Pepito prima', '987654321');
-- INSERT INTO usuario (activo, id_rol, clave, direccion, email, genero, nombre, notas, telefono) VALUES ('1', '2', 'clave3','Calle 2 # 2-4', 'asdesw@example.com', 'F', 'Jessica', 'Peluquera Principal', '232456789');
-- INSERT INTO usuario (activo, id_rol, clave, direccion, email, genero, nombre, notas, telefono) VALUES ('1', '3', 'clave4','Calle 3 # 2-5', 'qwedsa@example.com', 'M', 'Pablo', 'Administrador de Servicios', '123123231');

-- Agregar Servicios:

-- INSERT INTO servicio (activo, id_usuario, descripcion, duracion, precio) VALUES ('1', '3', 'Corte de Pelo (Hombre)', '1 hora', '1000');
-- INSERT INTO servicio (activo, id_usuario, descripcion, duracion, precio) VALUES ('1', '3', 'Corte de Pelo (Mujer)', '1 hora', '1250');

-- Agregar Citas:

-- INSERT INTO cita (activo, visita, fecha_hora, id_servicio, id_usuario, notas) VALUES ('1', '1', '23/12/30 11:00', '1', '1', 'Quisiera que la visita sea en Av. Avenida 1932');
-- INSERT INTO cita (activo, visita, fecha_hora, id_servicio, id_usuario, notas) VALUES ('1', '0', '23/12/30 09:00', '2', '2', 'Pago en efectivo');

-- Agregar Horarios de Atencion:
INSERT INTO peluqueria_db.hor_disp (hor_man_ap, hor_man_fin, hor_tar_ap, hor_tar_fin) VALUES ('8:00', '12:00', '15:00', '21:00');
