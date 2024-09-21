package br.unitins.tp2.cidades.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Estado extends DefaultEntity {

    @Column(length = 60, nullable = false)
    private String nome;

    @Column(length = 2, nullable = false)
    private String sigla;

    @Column(length = 60)
    private Long populacao;
    
    @OneToMany(mappedBy = "estado")
    private List<Cidade> cidades;
    
    @OneToOne
    @JoinColumn(name = "id_capital")
    private Cidade capital;

    public Cidade getCapital() {
        return capital;
    }
    public void setCapital(Cidade capital) {
        this.capital = capital;
    }
    public void setPopulacao(Long populacao) {
        this.populacao = populacao;
    }
    public Long getPopulacao() {
        return populacao;
    }
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getSigla() {
        return sigla;
    }
    
    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
    public List<Cidade> getCidades() {
        return cidades;
    }
    public void setCidades(List<Cidade> cidades) {
        this.cidades = cidades;
    }
}
