USE FDP_MARKET_DB;

-- ################
-- ### USUARIOS ###
-- ################
-- CLAVE: 123456
INSERT IGNORE INTO USUARIO (ID, CORREO, CLAVE_HASH, ID_ROL, ID_VENDEDOR_PADRE, COMISION_SUBVENDEDOR_PCT, SALDO_BILLETERA) VALUES
(1, 'ADMIN@FDP.COM', '$2A$12$J88EGU93.ICRHF62UHQUR.PTKILQLKAWOQPYKRQCCXDAUXLN9FL7O', 1, NULL, NULL, 0.00),
(2, 'MOD1@FDP.COM', '$2A$12$J88EGU93.ICRHF62UHQUR.PTKILQLKAWOQPYKRQCCXDAUXLN9FL7O', 2, NULL, NULL, 0.00),
(3, 'BOGLAGOLD@VENDOR.COM', '$2A$12$J88EGU93.ICRHF62UHQUR.PTKILQLKAWOQPYKRQCCXDAUXLN9FL7O', 3, NULL, NULL, 1500.50),
(4, 'WORKER1@VENDOR.COM', '$2A$12$J88EGU93.ICRHF62UHQUR.PTKILQLKAWOQPYKRQCCXDAUXLN9FL7O', 4, 3, 5.00, 45.00), 
(5, 'SYTHE_SELLER@VENDOR.COM', '$2A$12$J88EGU93.ICRHF62UHQUR.PTKILQLKAWOQPYKRQCCXDAUXLN9FL7O', 3, NULL, NULL, 800.00),
(6, 'CLIENTE_BALLER@MAIL.COM', '$2A$12$J88EGU93.ICRHF62UHQUR.PTKILQLKAWOQPYKRQCCXDAUXLN9FL7O', 5, NULL, NULL, 5000.00),
(7, 'CLIENTE_POBRE@MAIL.COM', '$2A$12$J88EGU93.ICRHF62UHQUR.PTKILQLKAWOQPYKRQCCXDAUXLN9FL7O', 5, NULL, NULL, 5.00);

-- #############################
-- ### ANUNCIOS / Multimedia ###
-- #############################
INSERT IGNORE INTO anuncio (id, id_usuario, id_categoria, titulo, descripcion, atributo_personalizado, precio_actual, stock_generico, tipo_entrega, activo) VALUES
(1, 4, 1, 'Gold', 'Venta directa de oro', NULL, 0.25, 5000, 'Manual', TRUE),
(2, 5, 2, 'Twisted Bow', 'Limpio, no flag', NULL, 395.00, 3, 'Manual', TRUE),
(3, 3, 6, 'Minigames - LMS', 'Jugado a mano', '1pt', 2.00, NULL, 'Manual', TRUE),
(4, 5, 4, 'Maxed Main 2277 TTL, 300 QP, 5 pets, Elite CA', 'Sin bans, original owner', NULL, 1150.00, NULL, 'Automatica', TRUE),
(5, 3, 8, 'Membership Bond', 'Se tradea ingame', NULL, 4.00, 1, 'Manual', TRUE),
(6, 5, 9, 'Prepaid 30d', 'Entrega instantánea', NULL, 10.00, NULL, 'Automatica', TRUE),
(7, 5, 1, 'Gold', 'Transferencia rápida, trades pequeños', NULL, 0.26, 2000, 'Manual', TRUE),
(8, 3, 2, 'Tumekens Shadow', 'Uncharged', NULL, 380.00, 1, 'Manual', TRUE),
(9, 4, 7, 'Combat Achievements - Elite', 'Requisitos en descripción. No parsec.', 'Full run', 45.00, NULL, 'Manual', TRUE),
(10, 5, 6, 'Minigames - LMS', 'Rank garantizado', '1 Win', 1.50, NULL, 'Manual', TRUE),
(11, 3, 4, '1 Def Pure 99 Str', 'Listo para PK. Questing básico.', NULL, 150.00, NULL, 'Automatica', TRUE),
(12, 5, 4, 'Ironman 1750 Total', 'Mid-game ironman con barrows', NULL, 250.00, NULL, 'Automatica', TRUE),
(13, 4, 10, 'Prepaid 60d', 'Código digital inmediato', NULL, 19.50, NULL, 'Automatica', TRUE),
(14, 5, 13, 'Amz. Prime 7d', 'Válido por 7 días de membresía', NULL, 1.50, NULL, 'Automatica', TRUE),
(15, 4, 2, 'Scythe of Vitur', 'Uncharged', NULL, 350.00, 2, 'Manual', TRUE),
(16, 5, 8, 'Membership Bond', 'Trade directo in-game', NULL, 4.10, 5, 'Manual', TRUE);

INSERT IGNORE INTO imagen_anuncio (id_anuncio, url) VALUES 
(1, 'https://oldschool.runescape.wiki/images/Coins_10000.png'),
(2, 'https://oldschool.runescape.wiki/images/Twisted_bow.png'),
(3, 'https://oldschool.runescape.wiki/images/Last_Man_Standing_logo.png'),
-- 6 imag cuenta id 4
(4, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(4, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(4, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(4, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(4, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(4, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(5, 'https://oldschool.runescape.wiki/images/Old_school_bond.png'),
-- 6 imag cuenta id 11
(11, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(11, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(11, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(11, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(11, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(11, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
-- 6 img cuenta id 12
(12, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(12, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(12, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(12, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(12, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(12, 'https://media.istockphoto.com/id/1980276924/vector/no-photo-thumbnail.jpg'),
(13, 'https://oldschool.runescape.wiki/images/Old_school_bond.png'),
(14, 'https://oldschool.runescape.wiki/images/Old_school_bond.png'),
(15, 'https://oldschool.runescape.wiki/images/Scythe_of_vitur.png'),
(16, 'https://oldschool.runescape.wiki/images/Old_school_bond.png');

-- ################
-- ### Seriales ###
-- ################
INSERT IGNORE INTO inventario_serial (id_anuncio, detalle_credencial, id_orden_asignada) VALUES 
(4, 'correo@mail.com:password123 | PIN:1234 | TOTP: ASDFQWERZXCV1234', NULL),
(6, 'OSRS-PREP-30D-XXXX-1111', NULL),
(6, 'OSRS-PREP-30D-XXXX-2222', NULL),
(6, 'OSRS-PREP-30D-XXXX-3333', NULL),
(6, 'OSRS-PREP-30D-XXXX-4444', NULL),
(6, 'OSRS-PREP-30D-XXXX-5555', NULL),
(6, 'OSRS-PREP-30D-XXXX-6666', NULL),
(6, 'OSRS-PREP-30D-XXXX-7777', NULL),
(6, 'OSRS-PREP-30D-XXXX-8888', NULL),
(6, 'OSRS-PREP-30D-XXXX-9999', NULL),
(11, 'pure1def@mail.com:pkpass123:0000:B1C2D3E4F5G6H7I8J9K0L1M2N3O4P5Q6', NULL),
(12, 'ironman@mail.com:fe_pass:9999:Z9Y8X7W6V5U4T3S2R1Q0P9O8N7M6L5K4', NULL),
(13, 'OSRS-PREP-60D-AAAA-1234', NULL),
(13, 'OSRS-PREP-60D-BBBB-5678', NULL),
(14, 'OSRS-AMZN-7D-CCCC-9101', NULL),
(14, 'OSRS-AMZN-7D-DDDD-1121', NULL),
(14, 'OSRS-AMZN-7D-EEEE-3141', NULL);

-- HU-03: Recarga
INSERT IGNORE INTO transaccion_ledger (id_usuario, tipo_movimiento, monto) VALUES (6, 'Deposito', 5000.00);

-- hu 11, orden compra carrito
-- Cliente 6 compra Anuncio 1 (Gold)
INSERT IGNORE INTO estado_orden (id, nombre) VALUES (2, 'Retenida_Escrow');
INSERT IGNORE INTO chat (id, id_tipo_chat) VALUES (1, 1);
INSERT IGNORE INTO chat_participante (id_chat, id_usuario) VALUES (1, 6), (1, 4);

-- HU-11/18: Orden y Chat (Orden 1)
INSERT IGNORE INTO chat (id, id_tipo_chat) VALUES (1, 1);
INSERT IGNORE INTO chat_participante (id_chat, id_usuario) VALUES (1, 6), (1, 4);
INSERT IGNORE INTO orden (id, id_cliente, id_anuncio, id_estado_orden, id_chat, cantidad, precio_unitario_historico, fee_plataforma, neto_vendedor_padre, comision_subvendedor) VALUES 
(1, 6, 1, 2, 1, 100, 0.25, 2.50, 20.00, 2.50);

-- Orden 2 (Cuenta ID 4)
INSERT IGNORE INTO chat (id, id_tipo_chat) VALUES (2, 1);
INSERT IGNORE INTO chat_participante (id_chat, id_usuario) VALUES (2, 6), (2, 5);
INSERT IGNORE INTO orden (id, id_cliente, id_anuncio, id_estado_orden, id_chat, cantidad, precio_unitario_historico, fee_plataforma, neto_vendedor_padre, comision_subvendedor) VALUES 
(2, 6, 4, 2, 2, 1, 1150.00, 10.00, 1140.00, 0.00);

-- HU-18/19: Mensaje y Evidencia
INSERT IGNORE INTO mensaje (id, id_chat, id_usuario_emisor, contenido) VALUES (1, 1, 6, 'Hola, estoy en Lumbridge F2P mundo 301');
INSERT IGNORE INTO evidencia (id_mensaje, url) VALUES (1, 'https://getquipu.com/wp-content/uploads/2021/10/02155904/plantilla-de-excel-recibo-de-pago-1.png');

-- HU-21: Disputa
UPDATE orden SET id_estado_orden = 5 WHERE id = 1;
INSERT IGNORE INTO disputa (id_orden, id_moderador, resolucion_tipo, justificacion_texto) VALUES 
(1, 2, NULL, 'Comprador reporta que no recibió el oro, vendedor no responde.');


