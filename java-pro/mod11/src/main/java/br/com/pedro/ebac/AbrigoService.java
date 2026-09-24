package br.com.pedro.ebac;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;import java.util.List;
@Service public class AbrigoService {
 private final FuncionarioRepository funcionarios;private final RacaRepository racas;private final AnimalRepository animais;private final EntityManager em;
 public AbrigoService(FuncionarioRepository f,RacaRepository r,AnimalRepository a,EntityManager em){funcionarios=f;racas=r;animais=a;this.em=em;}
 @Transactional public Funcionario funcionario(String nome){return funcionarios.save(new Funcionario(nome));}
 @Transactional public Raca raca(String nome,Especie especie){return racas.save(new Raca(nome,especie));}
 @Transactional public Animal resgatar(String nome,long racaId,long funcionarioId,LocalDate data){
  Raca raca=racas.findById(racaId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Raça não encontrada"));
  Funcionario funcionario=funcionarios.findById(funcionarioId).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,"Funcionário não encontrado"));
  return animais.save(new Animal(nome,raca,funcionario,data));
 }
 @Transactional(readOnly=true) public List<Animal> animais(){return em.createQuery("select a from Animal a join fetch a.raca join fetch a.funcionario order by a.id",Animal.class).getResultList();}
 @Transactional(readOnly=true) public List<Funcionario> funcionarios(){return funcionarios.findAll();}
 @Transactional(readOnly=true) public List<Raca> racas(){return racas.findAll();}
 @Transactional(readOnly=true) public List<TotalResgates> totais(LocalDate inicio,LocalDate fim){
  validarIntervalo(inicio,fim);
  return em.createQuery("select new br.com.pedro.ebac.TotalResgates(f.id,f.nome,count(a.id)) from Funcionario f left join Animal a on a.funcionario=f and a.dataResgate >= :inicio and a.dataResgate < :fim group by f.id,f.nome order by f.id",TotalResgates.class).setParameter("inicio",inicio).setParameter("fim",fim).getResultList();
 }
 public static void validarIntervalo(LocalDate inicio,LocalDate fim){if(inicio==null || fim==null || !fim.isAfter(inicio) || fim.isAfter(inicio.plusYears(1)))throw new IllegalArgumentException("Use início inclusivo e fim exclusivo, com duração máxima de um ano");}
}
