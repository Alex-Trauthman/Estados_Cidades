package br.unitins.tp2.cidades.repository;

import java.util.List;

import br.unitins.tp2.cidades.model.Cidade;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CidadeRepository implements PanacheRepository<Cidade> {
    
    public List<Cidade> findByEstado(Long id){
        return find("estado.id = ?1", id).list();
    }
    public List<Cidade> findByName(String nome){
        return find("UPPER(nome) LIKE ?1", "%"+ nome.toUpperCase() + "%").list();
    }
    public List<Cidade> findByEstadoAndName(Long id, String nome){
        return find("estado.id = ?1 AND UPPER(nome) LIKE ?2", id, "%"+ nome.toUpperCase() + "%").list();
    }
    public Cidade findByNomeCompleto(String nome){
        return find("UPPER(nome) = UPPER(?1)", nome).firstResult();
    }
}
