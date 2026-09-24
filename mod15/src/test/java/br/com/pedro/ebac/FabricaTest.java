package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
class FabricaTest {
    @Test void fabricaEletricaEntregaProdutosCompativeis() {
        FabricaCarros fabrica = new FabricaEletrica();
        assertEquals("elétrica", fabrica.criarCarro().familia());
        assertEquals(fabrica.criarCarro().familia(), fabrica.criarMotor().familia());
        assertEquals("bateria", fabrica.criarMotor().energia());
    }
    @Test void fabricaCombustaoEntregaProdutosCompativeis() {
        FabricaCarros fabrica = new FabricaCombustao();
        assertEquals("combustão", fabrica.criarCarro().familia());
        assertEquals(fabrica.criarCarro().familia(), fabrica.criarMotor().familia());
    }
}
