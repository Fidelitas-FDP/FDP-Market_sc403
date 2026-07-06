USE fdp_market_db;

-- ###########################
-- ### Catalogos: maestras ###
-- ###########################
CREATE TABLE IF NOT EXISTS rol (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE -- Visitante, Cliente, Vendedor, Sub-vendedor, Moderador
);

CREATE TABLE IF NOT EXISTS categoria (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE, -- Oro, Cuentas, Servicios, Codigos, Items
    id_padre BIGINT NULL, -- Soporte para subcategorías (ej. Códigos -> Bonds)
    FOREIGN KEY (id_padre) REFERENCES categoria(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS estado_orden (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE -- Pendiente Pago, Retenida, Completada, Disputada, Reembolsada
);

CREATE TABLE IF NOT EXISTS tipo_tiquete (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tipo_chat (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) NOT NULL UNIQUE -- Orden, Tiquete, Directo
);

-- ################################
-- ### Autenticacion / Usuarios ###
-- ################################
CREATE TABLE IF NOT EXISTS usuario (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    correo VARCHAR(150) NOT NULL UNIQUE,
    clave_hash VARCHAR(255) NOT NULL,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    id_rol BIGINT NOT NULL,
    id_vendedor_padre BIGINT NULL,
    comision_subvendedor_pct DECIMAL(5,2) NULL, -- Ej: 5.00
    saldo_billetera DECIMAL(12,2) DEFAULT 0.00 NOT NULL,
    FOREIGN KEY (id_rol) REFERENCES rol(id),
    FOREIGN KEY (id_vendedor_padre) REFERENCES usuario(id) ON DELETE SET NULL
);
CREATE INDEX idx_usuario_correo ON usuario(correo);

CREATE TABLE IF NOT EXISTS refresh_token (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    token VARCHAR(255) NOT NULL UNIQUE,
    fecha_expiracion DATETIME NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

-- #############################
-- ### Catalogo / Inventario ###
-- #############################
CREATE TABLE IF NOT EXISTS anuncio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    id_categoria BIGINT NOT NULL,
    titulo VARCHAR(150) NOT NULL,
    descripcion TEXT NOT NULL,
    atributo_personalizado VARCHAR(100) NULL, -- Campo extra para Servicios (Ej: "1pt")
    precio_actual DECIMAL(10,2) NOT NULL,
    stock_generico INT NULL, -- Modificado a NULL para permitir Servicios sin límite
    tipo_entrega VARCHAR(20) NOT NULL, -- Trasladado desde Categoría (Manual, Automatica)
    activo BOOLEAN DEFAULT TRUE NOT NULL,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE,
    FOREIGN KEY (id_categoria) REFERENCES categoria(id)
);
CREATE INDEX idx_anuncio_lectura ON anuncio(activo, id_categoria);

CREATE TABLE IF NOT EXISTS imagen_anuncio (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_anuncio BIGINT NOT NULL,
    url VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_anuncio) REFERENCES anuncio(id) ON DELETE CASCADE
);

-- #############################
-- ### Comunicacion - Chats ###
-- #############################
CREATE TABLE IF NOT EXISTS chat (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_tipo_chat BIGINT NOT NULL,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_tipo_chat) REFERENCES tipo_chat(id)
);

CREATE TABLE IF NOT EXISTS chat_participante (
    id_chat BIGINT NOT NULL,
    id_usuario BIGINT NOT NULL,
    PRIMARY KEY (id_chat, id_usuario),
    FOREIGN KEY (id_chat) REFERENCES chat(id) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id) ON DELETE CASCADE
);

-- ###################################
-- ### Transacciones - Escrow CORE ###
-- ###################################
CREATE TABLE IF NOT EXISTS orden (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_cliente BIGINT NOT NULL,
    id_anuncio BIGINT NOT NULL,
    id_estado_orden BIGINT NOT NULL,
    id_chat BIGINT NOT NULL,
    id_subvendedor_gestion BIGINT NULL,
    cantidad INT NOT NULL,
    precio_unitario_historico DECIMAL(10,2) NOT NULL,
    fee_plataforma DECIMAL(10,2) NOT NULL,
    neto_vendedor_padre DECIMAL(10,2) NOT NULL,
    comision_subvendedor DECIMAL(10,2) NOT NULL,
    rsn_comprador VARCHAR(50) NULL,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_liberacion DATETIME NULL,
    FOREIGN KEY (id_cliente) REFERENCES usuario(id),
    FOREIGN KEY (id_anuncio) REFERENCES anuncio(id),
    FOREIGN KEY (id_estado_orden) REFERENCES estado_orden(id),
    FOREIGN KEY (id_chat) REFERENCES chat(id),
    FOREIGN KEY (id_subvendedor_gestion) REFERENCES usuario(id) ON DELETE SET NULL
);
CREATE INDEX idx_orden_estado ON orden(id_estado_orden);

CREATE TABLE IF NOT EXISTS inventario_serial (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_anuncio BIGINT NOT NULL,
    detalle_credencial VARCHAR(255) NOT NULL,
    id_orden_asignada BIGINT NULL,
    FOREIGN KEY (id_anuncio) REFERENCES anuncio(id) ON DELETE CASCADE,
    FOREIGN KEY (id_orden_asignada) REFERENCES orden(id) ON DELETE SET NULL
);
CREATE INDEX idx_inventario_disponible ON inventario_serial(id_orden_asignada);

CREATE TABLE IF NOT EXISTS transaccion_ledger (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario BIGINT NOT NULL,
    tipo_movimiento VARCHAR(50) NOT NULL, -- Deposito, Retiro, Pago_Orden, Cobro_Venta, Comision
    monto DECIMAL(12,2) NOT NULL,
    id_orden_ref BIGINT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario) REFERENCES usuario(id),
    FOREIGN KEY (id_orden_ref) REFERENCES orden(id) ON DELETE SET NULL
);
CREATE INDEX idx_ledger_auditoria ON transaccion_ledger(id_usuario, fecha);

-- ##########################
-- ### Soporte / Disputas ###
-- ##########################
CREATE TABLE IF NOT EXISTS tiquete (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_usuario_creador BIGINT NOT NULL,
    id_tipo_tiquete BIGINT NOT NULL,
    id_chat BIGINT NOT NULL,
    asunto VARCHAR(150) NOT NULL,
    estado_abierto BOOLEAN DEFAULT TRUE NOT NULL,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_usuario_creador) REFERENCES usuario(id),
    FOREIGN KEY (id_tipo_tiquete) REFERENCES tipo_tiquete(id),
    FOREIGN KEY (id_chat) REFERENCES chat(id)
);

CREATE TABLE IF NOT EXISTS disputa (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_orden BIGINT NOT NULL UNIQUE,
    id_moderador BIGINT NULL,
    resolucion_tipo VARCHAR(50) NULL, -- Reembolso Total, Reembolso Parcial, Liberacion
    monto_reembolsado DECIMAL(10,2) NULL,
    justificacion_texto TEXT NULL,
    fecha_apertura DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_cierre DATETIME NULL,
    FOREIGN KEY (id_orden) REFERENCES orden(id),
    FOREIGN KEY (id_moderador) REFERENCES usuario(id) ON DELETE SET NULL
);

-- ##################
-- ### Mensajeria ###
-- ##################
CREATE TABLE IF NOT EXISTS mensaje (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_chat BIGINT NOT NULL,
    id_usuario_emisor BIGINT NOT NULL,
    contenido TEXT NOT NULL,
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_chat) REFERENCES chat(id) ON DELETE CASCADE,
    FOREIGN KEY (id_usuario_emisor) REFERENCES usuario(id)
);

CREATE TABLE IF NOT EXISTS evidencia (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    id_mensaje BIGINT NOT NULL,
    url VARCHAR(255) NOT NULL,
    FOREIGN KEY (id_mensaje) REFERENCES mensaje(id) ON DELETE CASCADE
);


-- ####################################
-- ############ DATOS BASE ############
-- ####################################

-- ################
-- ### Maestras ###
-- ################
INSERT IGNORE INTO rol (id, nombre) VALUES 
(1, 'Admin'), (2, 'Moderador'), (3, 'Vendedor'), (4, 'Sub-vendedor'), (5, 'Cliente');

INSERT IGNORE INTO estado_orden (id, nombre) VALUES 
(1, 'Pendiente_Pago'), (2, 'Retenida_Escrow'), (3, 'Entregada_Manual'), (4, 'Completada'), (5, 'Disputada'), (6, 'Reembolsada');

INSERT IGNORE INTO tipo_tiquete (id, nombre) VALUES 
(1, 'Error_Pago'), (2, 'Reporte_Usuario'), (3, 'Apelacion'), (4, 'Soporte_General');

INSERT IGNORE INTO tipo_chat (id, nombre) VALUES 
(1, 'Orden'), (2, 'Tiquete'), (3, 'Directo');


-- ################
-- ### Catalogos ##
-- ################
-- Padres
INSERT IGNORE INTO categoria (id, nombre, id_padre) VALUES 
(1, 'Oro', NULL), (2, 'Items', NULL), (3, 'Servicios', NULL), (4, 'Cuentas', NULL), (5, 'Codigos', NULL);

-- Hijos (Subcategorías)
INSERT IGNORE INTO categoria (id, nombre, id_padre) VALUES 
(6, 'Minigames - LMS', 3), 
(7, 'Combat Achievements - Elite', 3), 
(8, 'Membership Bond', 5), 
(9, 'Prepaid 30d', 5),
(10, 'Prepaid 60d', 5),
(11, 'Prepaid 90d', 5),
(12, 'Prepaid 120d', 5),
(13, 'Amz. Prime 7d', 5);