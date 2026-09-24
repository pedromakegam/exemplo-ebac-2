package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class CombinacoesETrocoTest {
    @Test void geraTodasAsCombinacoesSemRepetir() {
        assertEquals(List.of(List.of(1,2),List.of(1,3),List.of(2,3)), CombinacoesETroco.subconjuntos(new int[]{1,2,3},2));
        assertEquals(List.of(List.of()), CombinacoesETroco.subconjuntos(new int[]{1,2},0));
        assertEquals(List.of(List.of(1,2)), CombinacoesETroco.subconjuntos(new int[]{1,2},2));
    }
    @Test void validaConjuntoETamanho() {
        assertThrows(IllegalArgumentException.class, () -> CombinacoesETroco.subconjuntos(new int[]{1},2));
        assertThrows(IllegalArgumentException.class, () -> CombinacoesETroco.subconjuntos(new int[]{1,1},1));
    }
    @Test void devolveAsCincoMoedasDoExemplo() {
        assertEquals(List.of(5,5,5,2,1), CombinacoesETroco.troco(18));
        assertTrue(CombinacoesETroco.troco(0).isEmpty());
        assertThrows(IllegalArgumentException.class, () -> CombinacoesETroco.troco(-1));
    }
    @Test void confereGulosoContraSolucaoOtimaParaValoresAteCem() {
        int[] minimo = new int[101];
        for (int valor = 1; valor <= 100; valor++) {
            minimo[valor] = valor;
            for (int moeda : new int[]{1,2,5}) if (moeda <= valor) minimo[valor] = Math.min(minimo[valor], minimo[valor-moeda]+1);
            var moedas = CombinacoesETroco.troco(valor);
            assertEquals(valor, moedas.stream().mapToInt(Integer::intValue).sum());
            assertEquals(minimo[valor], moedas.size());
        }
    }
}
