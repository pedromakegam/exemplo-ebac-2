package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;
class PessoaTest {
    @Test void usaAsSubclassesPeloContratoComum() {
        Pessoa fisica = new PessoaFisica("Ana", "Rua A", "00000000000", LocalDate.of(1995,1,1));
        Pessoa juridica = new PessoaJuridica("Loja", "Rua B", "00000000000000", "Loja Ltda.");
        assertEquals("00000000000", fisica.getDocumento());
        assertEquals("00000000000000", juridica.getDocumento());
        assertEquals("Ana", fisica.getNome());
    }
    @Test void rejeitaNomeVazio() {
        assertThrows(IllegalArgumentException.class, () -> new PessoaJuridica(" ","Rua A","0","Loja"));
    }
}
