package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
@SpringBootTest @AutoConfigureMockMvc
class ApiTest {
    @Autowired MockMvc mvc;
    @Autowired ObjectMapper json;
    private final String valido = """
    {"nome":"Caderno","codigo":"P001","preco":15.90}
    """;
    @Test void executaCrudCompleto() throws Exception {
        String resposta=mvc.perform(post("/produtos").contentType("application/json").content(valido)).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        long id=json.readTree(resposta).get("id").asLong();
        mvc.perform(get("/produtos/"+id)).andExpect(status().isOk());
        mvc.perform(get("/produtos")).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").exists());
        mvc.perform(post("/produtos").contentType("application/json").content(valido)).andExpect(status().isConflict());
        mvc.perform(put("/produtos/"+id).contentType("application/json").content("""
        {"nome":"Nome atualizado","codigo":"P001","preco":18.90}
        """)).andExpect(status().isOk()).andExpect(jsonPath("$.nome").value("Nome atualizado"));
        mvc.perform(delete("/produtos/"+id)).andExpect(status().isNoContent());
        mvc.perform(get("/produtos/"+id)).andExpect(status().isNotFound());
    }
    @Test void rejeitaEntradaIncompleta() throws Exception {mvc.perform(post("/produtos").contentType("application/json").content("{}")).andExpect(status().isBadRequest());}
    @Test void atualizacaoAusenteRetorna404() throws Exception {mvc.perform(put("/produtos/999999").contentType("application/json").content(valido)).andExpect(status().isNotFound());}
}
