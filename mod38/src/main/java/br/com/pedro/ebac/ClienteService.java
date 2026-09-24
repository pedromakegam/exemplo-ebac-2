package br.com.pedro.ebac;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;
@ApplicationScoped public class ClienteService {
 @Inject ClienteRepository repository;
 public List<Cliente> listar(){return repository.listar();}
 public void salvar(Long id,String nome,String email){validar(nome,email);repository.salvar(id,nome.trim(),email.trim().toLowerCase(java.util.Locale.ROOT));}
 public void excluir(Long id){if(id==null)throw new IllegalArgumentException("Selecione um cliente");repository.excluir(id);}
 public static void validar(String nome,String email){
  if(nome==null || nome.isBlank() || nome.trim().length()>100)throw new IllegalArgumentException("Informe um nome de até 100 caracteres");
  if(email==null || email.length()>150 || !email.matches("[^\\s@]+@[^\\s@]+\\.[^\\s@]+"))throw new IllegalArgumentException("Informe um e-mail válido");
 }
}
