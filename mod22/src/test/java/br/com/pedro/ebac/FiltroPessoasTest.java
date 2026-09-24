package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class FiltroPessoasTest {
    @Test void retornaSomenteMulheresPreservandoNomesEOrdem() {
        List<Pessoa> entrada = FiltroPessoas.ler("Ana-F, Bruno-M, Carla-F");
        List<Pessoa> resultado = FiltroPessoas.mulheres(entrada);
        assertEquals(List.of("Ana", "Carla"), resultado.stream().map(Pessoa::nome).toList());
        assertTrue(resultado.stream().allMatch(p -> p.genero() == Pessoa.Genero.FEMININO));
        assertEquals(3, entrada.size());
        assertNotSame(entrada, resultado);
    }
    @Test void aceitaEntradaVaziaENenhumaMulher() {
        assertTrue(FiltroPessoas.mulheres(FiltroPessoas.ler("  ")).isEmpty());
        assertTrue(FiltroPessoas.mulheres(FiltroPessoas.ler("Bruno-M")).isEmpty());
    }
    @Test void aceitaEspacosMinusculasENomeComHifen() {
        assertEquals("Ana-Maria", FiltroPessoas.ler(" Ana-Maria - feminino ").get(0).nome());
    }
    @Test void rejeitaEntradaMalformada() {
        for (String entrada : List.of("Ana", "-F", "Ana-X", "Ana-F,"))
            assertThrows(IllegalArgumentException.class, () -> FiltroPessoas.ler(entrada));
    }
}
