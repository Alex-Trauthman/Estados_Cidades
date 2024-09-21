package br.unitins.tp2.cidades.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Cidade extends DefaultEntity {
    @Column(length = 60, nullable = false)
    private String nome;

    
    @Column(length = 12, nullable = false)
    private Long populacao;

    @ManyToOne
    @JoinColumn(name = "id_estado", nullable = false)
    private Estado estado;
    
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    public Long getPopulacao() {
        return populacao;
    }

    public void setPopulacao(Long populacao) {
        this.populacao = populacao;
    }
    public Estado getEstado() {
        return estado;
    }
    
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

}
