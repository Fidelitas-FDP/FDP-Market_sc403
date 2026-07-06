-- DROP DATABASE IF EXISTS fdp_market_db;
-- DROP USER IF EXISTS 'evaristo'@'localhost';

CREATE DATABASE IF NOT EXISTS fdp_market_db CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

CREATE USER IF NOT EXISTS 'evaristo'@'localhost' IDENTIFIED BY '1234';

GRANT ALL PRIVILEGES ON fdp_market_db.* TO 'evaristo'@'localhost';
FLUSH PRIVILEGES;