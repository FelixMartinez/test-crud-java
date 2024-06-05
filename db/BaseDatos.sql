-- Drop existing tables if they exist
DROP TABLE IF EXISTS movimientos;
DROP TABLE IF EXISTS cuentas;
DROP TABLE IF EXISTS clientes;

-- Table for Clients
CREATE TABLE clientes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    genero VARCHAR(50) NOT NULL,
    edad INT NOT NULL,
    identificacion VARCHAR(50) NOT NULL,
    direccion VARCHAR(255) NOT NULL,
    telefono VARCHAR(50) NOT NULL,
    clienteId VARCHAR(50) NOT NULL,
    contraseña VARCHAR(255) NOT NULL,
    estado BOOLEAN NOT NULL
);

-- Table for Accounts
CREATE TABLE cuentas (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    numero_cuenta VARCHAR(50) NOT NULL,
    tipo_cuenta VARCHAR(50) NOT NULL,
    saldo_inicial DOUBLE NOT NULL,
    estado BOOLEAN NOT NULL,
    cliente_id BIGINT,
    FOREIGN KEY (cliente_id) REFERENCES clientes(id)
);

-- Table for Movements
CREATE TABLE movimientos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    tipo_movimiento VARCHAR(50) NOT NULL,
    valor DOUBLE NOT NULL,
    saldo DOUBLE NOT NULL,
    cuenta_id BIGINT,
    FOREIGN KEY (cuenta_id) REFERENCES cuentas(id)
);

-- Insert initial data into Clients table
INSERT INTO clientes (nombre, genero, edad, identificacion, direccion, telefono, clienteId, contraseña, estado) VALUES
('Juan Perez', 'Masculino', 30, '123456789', 'Av. Siempre Viva 123', '987654321', 'juanp', 'password', TRUE);

-- Insert initial data into Accounts table
INSERT INTO cuentas (numero_cuenta, tipo_cuenta, saldo_inicial, estado, cliente_id) VALUES
('123456', 'Ahorro', 1000.0, TRUE, 1);

-- Insert initial data into Movements table
INSERT INTO movimientos (tipo_movimiento, valor, saldo, cuenta_id) VALUES
('Deposito', 500.0, 1500.0, 1),
('Retiro', -200.0, 1300.0, 1);

-- Select queries to verify the data
SELECT * FROM clientes;
SELECT * FROM cuentas;
SELECT * FROM movimientos;
