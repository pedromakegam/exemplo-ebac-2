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
    {"nome":"Ana Exemplo","cpf":"00000000001","email":"ana@example.invalid"}
    """;
    @Test void executaCrudCompleto() throws Exception {
        String resposta=mvc.perform(post("/clientes").contentType("application/json").content(valido)).andExpect(status().isCreated()).andReturn().getResponse().getContentAsString();
        long id=json.readTree(resposta).get("id").asLong();
        mvc.perform(get("/clientes/"+id)).andExpect(status().isOk());
        mvc.perform(get("/clientes")).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").exists());
        mvc.perform(post("/clientes").contentType("application/json").content(valido)).andExpect(status().isConflict());
        mvc.perform(put("/clientes/"+id).contentType("application/json").content("""
        {"nome":"Nome atualizado","cpf":"00000000001","email":"novo@example.invalid"}
        """)).andExpect(status().isOk()).andExpect(jsonPath("$.nome").value("Nome atualizado"));
        mvc.perform(delete("/clientes/"+id)).andExpect(status().isNoContent());
        mvc.perform(get("/clientes/"+id)).andExpect(status().isNotFound());
    }
    @Test void rejeitaEntradaIncompleta() throws Exception {mvc.perform(post("/clientes").contentType("application/json").content("{}")).andExpect(status().isBadRequest());}
    @Test void atualizacaoAusenteRetorna404() throws Exception {mvc.perform(put("/clientes/999999").contentType("application/json").content(valido)).andExpect(status().isNotFound());}
}
