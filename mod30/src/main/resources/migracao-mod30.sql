-- Aplicar no schema que contém as tabelas do projeto da aula.
-- Colunas opcionais preservam os cadastros existentes.
ALTER TABLE tb_cliente ADD COLUMN IF NOT EXISTS email varchar(150);
ALTER TABLE tb_produto ADD COLUMN IF NOT EXISTS categoria varchar(60);
