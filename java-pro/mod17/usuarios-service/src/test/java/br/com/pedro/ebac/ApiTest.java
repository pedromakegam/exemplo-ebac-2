package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;import org.springframework.test.web.servlet.MockMvc;import com.fasterxml.jackson.databind.ObjectMapper;import org.springframework.transaction.annotation.Transactional;
 import org.springframework.test.context.bean.override.mockito.MockitoBean;import static org.mockito.Mockito.*;import org.springframework.web.server.ResponseStatusException;import org.springframework.http.HttpStatus;
 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;import static org.junit.jupiter.api.Assertions.*;
 @SpringBootTest @AutoConfigureMockMvc @Transactional class ApiTest {
 @Autowired MockMvc mvc;@Autowired ObjectMapper json;@Autowired Repositorio repo;
 private final String valido="""
 {"nome":"Ana Exemplo","email":"ana@example.invalid"}
 """;
 @Test void cadastraConsultaELista() throws Exception {String body=mvc.perform(post("/memelandia/usuarios").contentType("application/json").content(valido)).andExpect(status().isCreated()).andExpect(jsonPath("$.dataCadastro").exists()).andReturn().getResponse().getContentAsString();long id=json.readTree(body).get("id").asLong();mvc.perform(get("/memelandia/usuarios/"+id)).andExpect(status().isOk());mvc.perform(get("/memelandia/usuarios")).andExpect(status().isOk()).andExpect(jsonPath("$[0].nome").exists());}
 @Test void rejeitaEntradaInvalida() throws Exception {mvc.perform(post("/memelandia/usuarios").contentType("application/json").content("{}")).andExpect(status().isBadRequest());assertEquals(0,repo.count());}
 @Test void ausenteRetorna404() throws Exception {mvc.perform(get("/memelandia/usuarios/999999")).andExpect(status().isNotFound());}
 @Test void expoeMetricas() throws Exception {mvc.perform(get("/actuator/metrics")).andExpect(status().isOk()).andExpect(jsonPath("$.names").isArray());}
 }
