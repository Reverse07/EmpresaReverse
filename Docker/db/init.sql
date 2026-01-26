-- Crear tabla Usuarios
CREATE TABLE IF NOT EXISTS usuarios (
    id BIGSERIAL PRIMARY KEY,
    apellido VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    estado BOOLEAN NOT NULL,
    fecha_creacion TIMESTAMP,
    nombre VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    rol VARCHAR(100) NOT NULL,
    telefono VARCHAR(50)
);

-- Crear tabla Servicios
CREATE TABLE IF NOT EXISTS servicios (
    id BIGSERIAL PRIMARY KEY,
    activo BOOLEAN NOT NULL,
    descripcion TEXT,
    duracion_estimada INTEGER,
    fecha_creacion TIMESTAMP,
    nombre VARCHAR(255) NOT NULL,
    precio NUMERIC(10,2) NOT NULL,
    tipo VARCHAR(100) NOT NULL
);

-- Crear tabla Pedidos
CREATE TABLE IF NOT EXISTS pedidos (
    id BIGSERIAL PRIMARY KEY,
    direccion TEXT NOT NULL,
    estado VARCHAR(100) NOT NULL,
    fecha_completada TIMESTAMP,
    fecha_programada TIMESTAMP,
    fecha_solicitud TIMESTAMP,
    notas_cliente TEXT,
    notas_internas TEXT,
    precio_final NUMERIC(10,2),
    cliente_id BIGINT NOT NULL REFERENCES usuarios(id),
    servicio_id BIGINT NOT NULL REFERENCES servicios(id)
);

-- Crear tabla Facturas
CREATE TABLE IF NOT EXISTS facturas (
    id BIGSERIAL PRIMARY KEY,
    fecha_emision TIMESTAMP,
    fecha_pago TIMESTAMP,
    igv NUMERIC(10,2) NOT NULL,
    metodo_pago VARCHAR(100),
    numero_factura VARCHAR(100) NOT NULL UNIQUE,
    pagada BOOLEAN NOT NULL,
    subtotal NUMERIC(10,2) NOT NULL,
    total NUMERIC(10,2) NOT NULL,
    pedido_id BIGINT NOT NULL REFERENCES pedidos(id)
);

-- Crear tabla Notificaciones
CREATE TABLE IF NOT EXISTS notificaciones (
    id BIGSERIAL PRIMARY KEY,
    fecha_creacion TIMESTAMP,
    fecha_lectura TIMESTAMP,
    leida BOOLEAN NOT NULL,
    mensaje TEXT NOT NULL,
    tipo VARCHAR(100),
    titulo VARCHAR(255) NOT NULL,
    destinatario_id BIGINT NOT NULL REFERENCES usuarios(id)
);

-- Crear tabla Tickets
CREATE TABLE IF NOT EXISTS tickets (
    id BIGSERIAL PRIMARY KEY,
    descripcion TEXT NOT NULL,
    estado VARCHAR(100) NOT NULL,
    fecha_creacion TIMESTAMP,
    fecha_resolucion TIMESTAMP,
    prioridad VARCHAR(100) NOT NULL,
    respuesta TEXT,
    titulo VARCHAR(255) NOT NULL,
    asesor_asignado_id BIGINT REFERENCES usuarios(id),
    pedido_id BIGINT REFERENCES pedidos(id),
    usuario_id BIGINT NOT NULL REFERENCES usuarios(id)
);

INSERT INTO usuarios (apellido, email, estado, fecha_creacion, nombre, password, rol, telefono) VALUES
('Pérez', 'juan.perez@mail.com', true, NOW(), 'Juan', '123456', 'CLIENTE', '999111222'),
('Gómez', 'ana.gomez@mail.com', true, NOW(), 'Ana', '123456', 'CLIENTE', '999222333'),
('Rojas', 'carlos.rojas@mail.com', true, NOW(), 'Carlos', '123456', 'ASESOR', '999333444'),
('Torres', 'lucia.torres@mail.com', true, NOW(), 'Lucía', '123456', 'ASESOR', '999444555'),
('Admin', 'admin@mail.com', true, NOW(), 'Administrador', 'admin123', 'ADMIN', '999555666');

INSERT INTO servicios (activo, descripcion, duracion_estimada, fecha_creacion, nombre, precio, tipo) VALUES
(true, 'Instalación de internet hogar', 120, NOW(), 'Internet Hogar', 120.00, 'INSTALACION'),
(true, 'Mantenimiento de red', 90, NOW(), 'Mantenimiento Red', 80.00, 'MANTENIMIENTO'),
(true, 'Soporte técnico remoto', 60, NOW(), 'Soporte Remoto', 50.00, 'SOPORTE'),
(true, 'Configuración de router', 45, NOW(), 'Config Router', 40.00, 'CONFIGURACION'),
(true, 'Instalación de cámaras', 180, NOW(), 'Cámaras Seguridad', 300.00, 'INSTALACION');

INSERT INTO pedidos (
    direccion, estado, fecha_completada, fecha_programada, fecha_solicitud,
    notas_cliente, notas_internas, precio_final, cliente_id, servicio_id
) VALUES
('Av. Lima 123', 'PENDIENTE', NULL, NOW() + INTERVAL '2 days', NOW(),
 'Por favor en la mañana', NULL, 120.00, 1, 1),

('Jr. Arequipa 456', 'PROGRAMADO', NULL, NOW() + INTERVAL '1 day', NOW(),
 NULL, 'Asignar técnico senior', 80.00, 2, 2),

('Calle Sol 789', 'COMPLETADO', NOW(), NOW() - INTERVAL '1 day', NOW() - INTERVAL '3 days',
 NULL, NULL, 50.00, 1, 3),

('Av. Grau 321', 'PENDIENTE', NULL, NOW() + INTERVAL '3 days', NOW(),
 'Llevar cable extra', NULL, 40.00, 2, 4),

('Av. Central 999', 'PROGRAMADO', NULL, NOW() + INTERVAL '5 days', NOW(),
 NULL, 'Instalación compleja', 300.00, 1, 5);

    INSERT INTO facturas (
    fecha_emision, fecha_pago, igv, metodo_pago, numero_factura,
    pagada, subtotal, total, pedido_id
) VALUES
(NOW(), NULL, 21.60, 'TARJETA', 'F001-0001', false, 120.00, 141.60, 1),
(NOW(), NOW(), 14.40, 'EFECTIVO', 'F001-0002', true, 80.00, 94.40, 2),
(NOW(), NOW(), 9.00, 'YAPE', 'F001-0003', true, 50.00, 59.00, 3),
(NOW(), NULL, 7.20, 'PLIN', 'F001-0004', false, 40.00, 47.20, 4),
(NOW(), NULL, 54.00, 'TRANSFERENCIA', 'F001-0005', false, 300.00, 354.00, 5);

INSERT INTO notificaciones (
    fecha_creacion, fecha_lectura, leida, mensaje, tipo, titulo, destinatario_id
) VALUES
(NOW(), NULL, false, 'Su pedido fue registrado', 'INFO', 'Pedido creado', 1),
(NOW(), NULL, false, 'Tiene un nuevo pedido asignado', 'ALERTA', 'Nuevo pedido', 3),
(NOW(), NOW(), true, 'Su factura fue pagada', 'INFO', 'Pago confirmado', 2),
(NOW(), NULL, false, 'Pedido programado para mañana', 'RECORDATORIO', 'Pedido programado', 1),
(NOW(), NULL, false, 'Nuevo ticket de soporte', 'ALERTA', 'Ticket creado', 4);


INSERT INTO tickets (
    descripcion, estado, fecha_creacion, fecha_resolucion,
    prioridad, respuesta, titulo, asesor_asignado_id, pedido_id, usuario_id
) VALUES
('Internet lento después de instalación', 'ABIERTO', NOW(), NULL,
 'ALTA', NULL, 'Problema de velocidad', 3, 1, 1),

('Router no enciende', 'EN_PROCESO', NOW(), NULL,
 'MEDIA', 'Revisando equipo', 'Falla de router', 4, 4, 2),

('Consulta sobre factura', 'RESUELTO', NOW() - INTERVAL '2 days', NOW(),
 'BAJA', 'Factura explicada', 'Consulta de factura', 3, 3, 1),

('Cámara no graba', 'ABIERTO', NOW(), NULL,
 'ALTA', NULL, 'Error en cámara', 4, 5, 2),

('Duda sobre programación', 'RESUELTO', NOW() - INTERVAL '1 day', NOW(),
 'BAJA', 'Fecha confirmada', 'Consulta programación', 3, 2, 2);
