-- Active: 1702505723442@@127.0.0.1@3306@peluqueria_db

-- Ingresar Roles para los usuarios:
INSERT INTO rol (descripcion) VALUES ('USUARIO');
INSERT INTO rol (descripcion) VALUES ('ESTILISTA');
INSERT INTO rol (descripcion) VALUES ('ADMINISTRADOR');

-- Agregar Usuarios:
--INSERT INTO usuario (activo, id_rol, telefono, nombre, clave, direccion, email, notas, genero) VALUES ('1', '1', '+543625233221', 'Cliente Uno', 'cliente1', 'Av Avenida 123', 'clienteuno@email.com', 'nota1', 'F');
--INSERT INTO usuario (activo, id_rol, telefono, nombre, clave, direccion, email, notas, genero) VALUES ('1', '1', '+543625233222', 'Cliente Dos', 'Cliente2', 'Calle 21 321', 'clientedos@email.com', 'nota2', 'M');
-- Agregar Estilistas:
--INSERT INTO usuario (activo, id_rol, telefono, nombre, clave, direccion, email, notas, genero) VALUES ('1', '2', '+543625233223', 'Estilista Uno', 'estilista1', 'Calle 12 212', 'estilistauno@email.com', 'nota3', 'F');
--INSERT INTO usuario (activo, id_rol, telefono, nombre, clave, direccion, email, notas, genero) VALUES ('1', '2', '+543625233224', 'Estilista Dos', 'estilista2', 'Calle 14 1932', 'estilistados@email.com', 'nota4', 'M');
-- Agregar Administradores:
--INSERT INTO usuario (activo, id_rol, telefono, nombre, clave, direccion, email, notas, genero) VALUES ('1', '3', '+543625233225', 'Admin Uno', 'admin1', 'Calle 9 2112', 'adminuno@email.com', 'nota5', 'F');

-- Agregar Servicios:

INSERT INTO servicio (activo, precio, duracion, descripcion) VALUES ('1', '2000', '1:00', 'Corte de Cabello');
INSERT INTO servicio (activo, precio, duracion, descripcion) VALUES ('1', '3000', '2:00', 'Teñido de Cabello');

-- Agregar Citas:

--INSERT INTO cita (visita, fecha_hora, servicio_id, usuario_id, notas) VALUES ('1', '2024/1/11 20:00', '1', '1', 'pago por mercadopago');
--INSERT INTO cita (visita, fecha_hora, servicio_id, usuario_id, notas) VALUES ('1', '2024/1/12 9:00', '2', '2', 'pago en efectivo');


-- Agregar Horarios de Atencion:
INSERT INTO hor_disp (hor_man_ap, hor_man_fin, hor_tar_ap, hor_tar_fin) VALUES ('8:00', '12:00', '15:00', '21:00');
