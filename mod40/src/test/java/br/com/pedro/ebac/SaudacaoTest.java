package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc
class SaudacaoTest {
    @Autowired MockMvc mvc;
    @Test void respondeSaudacao() throws Exception {mvc.perform(get("/saudacao").param("nome","Pedro")).andExpect(status().isOk()).andExpect(jsonPath("$.mensagem").value("Olá, Pedro!"));}
    @Test void rejeitaNomeEmBranco() throws Exception {mvc.perform(get("/saudacao").param("nome"," ")).andExpect(status().isBadRequest());}
    @Test void informaSaude() throws Exception {mvc.perform(get("/actuator/health")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("UP"));}
}
