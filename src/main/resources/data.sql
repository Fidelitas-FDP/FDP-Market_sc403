USE fdp_market_db;

-- ################
-- ### Usuarios ###
-- ################
-- clave: 123456
INSERT IGNORE INTO usuario (id, correo, clave_hash, id_rol, id_vendedor_padre, comision_subvendedor_pct, saldo_billetera) VALUES
(1, 'admin@fdp.com', '$2a$12$j88egu93.ICRHf62UHQur.pTkIlqLkAwoqPYKRQCcxDAUXlN9fL7O', 1, NULL, NULL, 0.00),
(2, 'mod1@fdp.com', '$2a$12$j88egu93.ICRHf62UHQur.pTkIlqLkAwoqPYKRQCcxDAUXlN9fL7O', 2, NULL, NULL, 0.00),
(3, 'boglagold@vendor.com', '$2a$12$j88egu93.ICRHf62UHQur.pTkIlqLkAwoqPYKRQCcxDAUXlN9fL7O', 3, NULL, NULL, 1500.50),
(4, 'worker1@vendor.com', '$2a$12$j88egu93.ICRHf62UHQur.pTkIlqLkAwoqPYKRQCcxDAUXlN9fL7O', 4, 3, 5.00, 45.00), 
(5, 'sythe_seller@vendor.com', '$2a$12$j88egu93.ICRHf62UHQur.pTkIlqLkAwoqPYKRQCcxDAUXlN9fL7O', 3, NULL, NULL, 800.00),
(6, 'cliente_baller@mail.com', '$2a$12$j88egu93.ICRHf62UHQur.pTkIlqLkAwoqPYKRQCcxDAUXlN9fL7O', 5, NULL, NULL, 5000.00),
(7, 'cliente_pobre@mail.com', '$2a$12$j88egu93.ICRHf62UHQur.pTkIlqLkAwoqPYKRQCcxDAUXlN9fL7O', 5, NULL, NULL, 5.00);

-- #############################
-- ### Anuncios / Multimedia ###
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
(11, 'pure1def@mail.com:pkpass123:0000(pin):B1C2D3E4F5G6H7I8J9K0L1M2N3O4P5Q6(32 digitos)', NULL),
(12, 'ironman@mail.com:fe_pass:9999(pin):Z9Y8X7W6V5U4T3S2R1Q0P9O8N7M6L5K4(32 digitos)', NULL),
(13, 'OSRS-PREP-60D-AAAA-1234', NULL),
(13, 'OSRS-PREP-60D-BBBB-5678', NULL),
(14, 'OSRS-AMZN-7D-CCCC-9101', NULL),
(14, 'OSRS-AMZN-7D-DDDD-1121', NULL),
(14, 'OSRS-AMZN-7D-EEEE-3141', NULL);