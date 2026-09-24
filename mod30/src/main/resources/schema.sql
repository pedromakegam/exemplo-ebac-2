CREATE TABLE cliente (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cpf char(11) NOT NULL UNIQUE,
    nome varchar(100) NOT NULL
, email varchar(150) NOT NULL
);
CREATE TABLE produto (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    codigo varchar(20) NOT NULL UNIQUE,
    nome varchar(100) NOT NULL,
    preco numeric(12,2) NOT NULL CHECK (preco >= 0)
, categoria varchar(60) NOT NULL
);

CREATE TABLE venda (
    id bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    cliente_id bigint NOT NULL REFERENCES cliente(id),
    criada_em timestamptz NOT NULL DEFAULT NOW()
);
CREATE TABLE item_venda (
    venda_id bigint NOT NULL REFERENCES venda(id),
    produto_id bigint NOT NULL REFERENCES produto(id),
    quantidade integer NOT NULL CHECK (quantidade > 0),
    preco_unitario numeric(12,2) NOT NULL CHECK (preco_unitario >= 0),
    PRIMARY KEY (venda_id, produto_id)
);
