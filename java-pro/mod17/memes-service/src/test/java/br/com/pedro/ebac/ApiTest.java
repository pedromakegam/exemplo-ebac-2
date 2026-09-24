package br.com.pedro.ebac;

import org.junit.jupiter.api.Test;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.boot.test.context.SpringBootTest;import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;import org.springframework.test.web.servlet.MockMvc;import com.fasterxml.jackson.databind.ObjectMapper;import org.springframework.transaction.annotation.Transactional;
 import org.springframework.test.context.bean.override.mockito.MockitoBean;import static org.mockito.Mockito.*;import org.springframework.web.server.ResponseStatusException;import org.springframework.http.HttpStatus;
 import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;import static org.junit.jupiter.api.Assertions.*;
 @SpringBootTest @AutoConfigureMockMvc @Transactional class ApiTest {
 @Autowired MockMvc mvc;@Autowired ObjectMapper json;@Autowired Repositorio repo;@MockitoBean Referencias referencias;
 private final String valido="""
 {"nome":"Meme de exemplo","descricao":"Exemplo fictício","url":"https://example.com/meme.png","usuarioId":1,"categoriaId":1}
 """;
 @Test void cadastraConsultaELista() throws Exception {String body=mvc.perform(post("/memelandia/memes").contentType("application/json").content(valido)).andExpect(status().isCreated()).andExpect(jsonPath("$.dataCadastro").exists()).andReturn().getResponse().getContentAsString();long id=json.readTree(body).get("id").asLong();mvc.perform(get("/memelandia/memes/"+id)).andExpect(status().isOk());mvc.perform(get("/memelandia/memes")).andExpect(status().isOk()).andExpect(jsonPath("$[0].nome").exists());verify(referencias).usuario(1);verify(referencias).categoria(1);}
 @Test void rejeitaEntradaInvalida() throws Exception {mvc.perform(post("/memelandia/memes").contentType("application/json").content("{}")).andExpect(status().isBadRequest());assertEquals(0,repo.count());}
 @Test void ausenteRetorna404() throws Exception {mvc.perform(get("/memelandia/memes/999999")).andExpect(status().isNotFound());}
 @Test void expoeMetricas() throws Exception {mvc.perform(get("/actuator/metrics")).andExpect(status().isOk()).andExpect(jsonPath("$.names").isArray());}
 @Test void naoGravaSemReferencia() throws Exception {doThrow(new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY)).when(referencias).usuario(1);mvc.perform(post("/memelandia/memes").contentType("application/json").content(valido)).andExpect(status().isUnprocessableEntity());assertEquals(0,repo.count());}
 @Test void naoGravaSeDependenciaFalhar() throws Exception {doThrow(new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE)).when(referencias).usuario(1);mvc.perform(post("/memelandia/memes").contentType("application/json").content(valido)).andExpect(status().isServiceUnavailable());assertEquals(0,repo.count());}
 @Test void naoGravaSemCategoria() throws Exception {doThrow(new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY)).when(referencias).categoria(1);mvc.perform(post("/memelandia/memes").contentType("application/json").content(valido)).andExpect(status().isUnprocessableEntity());assertEquals(0,repo.count());}
 @Test void rejeitaUrlForaDeHttp(){assertThrows(IllegalArgumentException.class,()->Servico.validarUrl("file:///etc/passwd"));}
 @Test void aleatorioVazioRetorna404() throws Exception {mvc.perform(get("/memelandia/memes/aleatorio")).andExpect(status().isNotFound());}
 @Test void aleatorioRetornaMemeExistente() throws Exception {mvc.perform(post("/memelandia/memes").contentType("application/json").content(valido)).andExpect(status().isCreated());mvc.perform(get("/memelandia/memes/aleatorio")).andExpect(status().isOk()).andExpect(jsonPath("$.nome").value("Meme de exemplo"));}
 }
