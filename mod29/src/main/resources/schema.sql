CREATE TABLE cliente (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cpf char(11) NOT NULL UNIQUE,
    nome varchar(100) NOT NULL

);
CREATE TABLE produto (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo varchar(20) NOT NULL UNIQUE,
    nome varchar(100) NOT NULL,
    preco numeric(12,2) NOT NULL CHECK (preco >= 0)

);
