CREATE SCHEMA mod28;
SET search_path TO mod28;
CREATE TABLE "Cliente" (
    "Id" bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "Cpf" char(11) NOT NULL UNIQUE,
    "Nome" varchar(100) NOT NULL,
    "Email" varchar(150) NOT NULL
);
CREATE TABLE "Produto" (
    "Id" bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "Codigo" varchar(20) NOT NULL UNIQUE,
    "Nome" varchar(100) NOT NULL,
    "Preco" numeric(12,2) NOT NULL CHECK ("Preco" >= 0)
);
CREATE TABLE "Venda" (
    "Id" bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    "ClienteId" bigint NOT NULL REFERENCES "Cliente"("Id"),
    "Data" timestamptz NOT NULL DEFAULT NOW(),
    "Status" varchar(12) NOT NULL DEFAULT 'CONCLUIDA'
        CHECK ("Status" IN ('INICIADA', 'CONCLUIDA', 'CANCELADA'))
);
CREATE TABLE "ItemVenda" (
    "VendaId" bigint NOT NULL REFERENCES "Venda"("Id"),
    "ProdutoId" bigint NOT NULL REFERENCES "Produto"("Id"),
    "Quantidade" integer NOT NULL CHECK ("Quantidade" > 0),
    "PrecoUnitario" numeric(12,2) NOT NULL CHECK ("PrecoUnitario" >= 0),
    PRIMARY KEY ("VendaId", "ProdutoId")
);
CREATE INDEX "ix_venda_cliente" ON "Venda"("ClienteId");
CREATE INDEX "ix_item_produto" ON "ItemVenda"("ProdutoId");

BEGIN;
INSERT INTO "Cliente" ("Cpf", "Nome", "Email") VALUES
    ('00000000001', 'Ana Exemplo', 'ana@example.invalid'),
    ('00000000002', 'Bruno Exemplo', 'bruno@example.invalid');
INSERT INTO "Produto" ("Codigo", "Nome", "Preco") VALUES
    ('P001', 'Caderno', 16.90), ('P002', 'Caneta', 3.50), ('P003', 'Borracha', 2.00);
INSERT INTO "Venda" ("ClienteId") SELECT "Id" FROM "Cliente" WHERE "Cpf" = '00000000001';
INSERT INTO "Venda" ("ClienteId") SELECT "Id" FROM "Cliente" WHERE "Cpf" = '00000000002';
INSERT INTO "ItemVenda" ("VendaId", "ProdutoId", "Quantidade", "PrecoUnitario")
VALUES (1, 1, 2, 16.90), (1, 2, 3, 3.50), (2, 2, 2, 3.50);
COMMIT;

SELECT v."Id" AS "Venda", c."Nome" AS "Cliente", p."Nome" AS "Produto",
    i."Quantidade", i."PrecoUnitario", i."Quantidade" * i."PrecoUnitario" AS "Subtotal"
FROM "Venda" v JOIN "Cliente" c ON c."Id" = v."ClienteId"
JOIN "ItemVenda" i ON i."VendaId" = v."Id"
JOIN "Produto" p ON p."Id" = i."ProdutoId" ORDER BY v."Id", p."Id";

SELECT v."Id" AS "Venda", c."Nome" AS "Cliente",
    SUM(i."Quantidade" * i."PrecoUnitario") AS "Total"
FROM "Venda" v JOIN "Cliente" c ON c."Id" = v."ClienteId"
JOIN "ItemVenda" i ON i."VendaId" = v."Id"
GROUP BY v."Id", c."Nome" ORDER BY v."Id";

SELECT p."Nome", COALESCE(SUM(i."Quantidade"), 0) AS "QuantidadeVendida"
FROM "Produto" p LEFT JOIN "ItemVenda" i ON i."ProdutoId" = p."Id"
GROUP BY p."Id", p."Nome" ORDER BY p."Id";

DO $$
BEGIN
    BEGIN
        INSERT INTO "Venda" ("ClienteId") VALUES (-1);
        RAISE EXCEPTION 'Falha: cliente inexistente foi aceito';
    EXCEPTION WHEN foreign_key_violation THEN
        RAISE NOTICE 'OK: chave estrangeira rejeitou cliente inexistente';
    END;
    BEGIN
        INSERT INTO "ItemVenda" VALUES (2, 3, 0, 2.00);
        RAISE EXCEPTION 'Falha: quantidade zero foi aceita';
    EXCEPTION WHEN check_violation THEN
        RAISE NOTICE 'OK: CHECK rejeitou quantidade zero';
    END;
END $$;
