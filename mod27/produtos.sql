CREATE SCHEMA mod27;
SET search_path TO mod27;

CREATE TABLE "Produto" (
    "Id" bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "Codigo" varchar(20) NOT NULL UNIQUE,
    "Nome" varchar(100) NOT NULL,
    "Descricao" varchar(300),
    "Preco" numeric(12,2) NOT NULL CHECK ("Preco" >= 0)
);

ALTER TABLE "Produto" ADD COLUMN "Ativo" boolean NOT NULL DEFAULT true;

INSERT INTO "Produto" ("Codigo", "Nome", "Descricao", "Preco") VALUES
    ('P001', 'Caderno', 'Caderno de exemplo, 80 folhas', 15.90),
    ('P002', 'Caneta', 'Caneta azul de exemplo', 3.50),
    ('TEMP', 'Produto temporário', 'Registro para testar DELETE', 1.00);

SELECT "Codigo", "Nome", "Preco", "Ativo" FROM "Produto" ORDER BY "Codigo";

UPDATE "Produto" SET "Preco" = 16.90 WHERE "Codigo" = 'P001';
DELETE FROM "Produto" WHERE "Codigo" = 'TEMP';

SELECT "Codigo", "Nome", "Preco", "Ativo" FROM "Produto" ORDER BY "Codigo";

CREATE TABLE "ProdutoRascunho" ("Id" integer PRIMARY KEY);
DROP TABLE "ProdutoRascunho";

SELECT table_name FROM information_schema.tables
WHERE table_schema = 'mod27' ORDER BY table_name;
