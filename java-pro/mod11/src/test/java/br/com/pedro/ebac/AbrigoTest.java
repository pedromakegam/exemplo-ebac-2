package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;import static org.junit.jupiter.api.Assertions.*;import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc @Transactional class AbrigoTest {
 @Autowired AbrigoService service;@Autowired MockMvc mvc;
 @Test void contaCaesEGatosComLimitesDeDataEFuncionarioSemResgate(){
  var ana=service.funcionario("Ana Exemplo");var bia=service.funcionario("Bia Exemplo");var gato=service.raca("Sem raça definida",Especie.GATO);var cao=service.raca("Sem raça definida",Especie.CACHORRO);
  service.resgatar("Mimi",gato.getId(),ana.getId(),LocalDate.parse("2024-01-01"));service.resgatar("Rex",cao.getId(),ana.getId(),LocalDate.parse("2024-12-31"));service.resgatar("Fora",gato.getId(),ana.getId(),LocalDate.parse("2025-01-01"));
  var lista=service.totais(LocalDate.parse("2024-01-01"),LocalDate.parse("2025-01-01"));assertEquals(2,lista.size());assertEquals(2,lista.get(0).quantidade());assertEquals(0,lista.get(1).quantidade());assertEquals(3,service.animais().size());
 }
 @Test void rejeitaMaisDeUmAno(){assertThrows(IllegalArgumentException.class,()->AbrigoService.validarIntervalo(LocalDate.parse("2024-01-01"),LocalDate.parse("2025-01-02")));}
 @Test void aceitaAnoBissexto(){assertDoesNotThrow(()->AbrigoService.validarIntervalo(LocalDate.parse("2024-01-01"),LocalDate.parse("2025-01-01")));}
 @Test void rejeitaIntervaloInvertido(){assertThrows(IllegalArgumentException.class,()->AbrigoService.validarIntervalo(LocalDate.parse("2025-01-01"),LocalDate.parse("2024-01-01")));}
 @Test void apiRejeitaCamposAusentes() throws Exception {mvc.perform(post("/animais").contentType("application/json").content("{}")).andExpect(status().isBadRequest());}
 @Test void apiRejeitaReferenciaAusente() throws Exception {mvc.perform(post("/animais").contentType("application/json").content("{\"nome\":\"Mimi\",\"racaId\":999,\"funcionarioId\":999,\"dataResgate\":\"2024-01-01\"}")).andExpect(status().isNotFound());}
 @Test void apiRejeitaIntervaloLongo() throws Exception {mvc.perform(get("/relatorios/resgates").param("inicio","2024-01-01").param("fim","2025-01-02")).andExpect(status().isBadRequest());}
}
