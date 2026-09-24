# Três dos 12 fatores

## 1. Configuração fora do código

Endereços de serviços, credenciais e opções de ambiente não devem ficar fixos no código. Separar a configuração permite usar o mesmo artefato em desenvolvimento e produção. Em microsserviços, isso é ainda mais útil porque cada serviço pode ter uma configuração diferente. No monólito, o princípio também vale; a diferença é a quantidade de unidades configuradas e implantadas.

## 2. Dependências declaradas

As dependências devem estar descritas no projeto, com versões reproduzíveis, sem depender de bibliotecas instaladas informalmente na máquina. Em Java, o pom.xml e o gerenciamento de dependências cumprem esse papel. Cada microsserviço pode evoluir suas bibliotecas, respeitando compatibilidade e segurança. No monólito, os módulos normalmente compartilham uma implantação e precisam conciliar essas versões dentro do mesmo artefato.

## 3. Processos sem estado local indispensável

Um processo não deve guardar exclusivamente em sua memória ou em seu disco local os dados de que outra instância precisa. O estado durável fica em um serviço de armazenamento apropriado. Assim, uma instância pode ser substituída ou replicada sem perder o funcionamento do sistema. Isso ajuda o escalonamento horizontal tanto de microsserviços quanto de monólitos. A diferença é que, com serviços separados, a escala pode ser ajustada por domínio.

## Referência

The Twelve-Factor App - fatores II, III e VI. https://12factor.net/
