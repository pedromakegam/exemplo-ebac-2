package br.com.pedro.ebac;

import jakarta.inject.*;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.context.FacesContext;
import jakarta.faces.application.FacesMessage;
import jakarta.annotation.PostConstruct;
import java.io.Serializable;
import java.util.List;
@Named("clientes") @ViewScoped public class ClienteController implements Serializable {
 @Inject ClienteService service;
 private Long id;private String nome="",email="";private List<Cliente> lista;
 @PostConstruct public void carregar(){try{lista=service.listar();}catch(RuntimeException e){lista=List.of();mensagem(true,"Não foi possível consultar o banco.");}}
 public void salvar(){try{service.salvar(id,nome,email);limpar();carregar();mensagem(false,"Cliente salvo.");}catch(IllegalArgumentException e){mensagem(true,e.getMessage());}catch(RuntimeException e){mensagem(true,"Não foi possível salvar. Verifique se o e-mail já foi cadastrado.");}}
 public void editar(Cliente c){id=c.getId();nome=c.getNome();email=c.getEmail();}
 public void excluir(Cliente c){try{service.excluir(c.getId());limpar();carregar();mensagem(false,"Cliente excluído.");}catch(RuntimeException e){mensagem(true,"Não foi possível excluir o cliente.");}}
 public void limpar(){id=null;nome="";email="";}
 private void mensagem(boolean erro,String text){FacesContext.getCurrentInstance().addMessage(null,new FacesMessage(erro?FacesMessage.SEVERITY_ERROR:FacesMessage.SEVERITY_INFO,text,null));}
 public List<Cliente> getLista(){return lista;}public Long getId(){return id;}
 public String getNome(){return nome;}public void setNome(String value){nome=value;}
 public String getEmail(){return email;}public void setEmail(String value){email=value;}
}
