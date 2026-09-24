# Clientes, produtos e vendas

Execute `psql -v ON_ERROR_STOP=1 -a -f mod28/vendas.sql` em um banco de exercícios sem o schema mod28. São usados dados fictícios.

Cliente tem várias vendas; Venda tem vários itens; Produto pode aparecer em vários itens. ItemVenda resolve o relacionamento entre Venda e Produto e guarda quantidade e preço no momento da compra. Assim, mudar o preço atual do produto não muda o histórico das vendas.

Chaves estrangeiras impedem referências inexistentes. UNIQUE protege CPF e código; CHECK valida preço e quantidade. O script inclui consultas com JOIN, agregação e LEFT JOIN, além de verificar rejeições de quantidade inválida e cliente inexistente. O total é calculado a partir dos itens.
