package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class ValidacaoTest {
 @Test void aceitaCadastroValido(){assertDoesNotThrow(()->ClienteService.validar("Ana Exemplo","ana@example.invalid"));}
 @Test void rejeitaNomeVazio(){assertThrows(IllegalArgumentException.class,()->ClienteService.validar(" ","ana@example.invalid"));}
 @Test void rejeitaEmailInvalido(){assertThrows(IllegalArgumentException.class,()->ClienteService.validar("Ana","email incorreto"));}
}
