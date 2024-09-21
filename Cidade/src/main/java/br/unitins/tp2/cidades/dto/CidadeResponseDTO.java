package br.unitins.tp2.cidades.dto;

import br.unitins.tp2.cidades.model.Cidade;

public record CidadeResponseDTO(
    Long id,
    String nome,
    
    Long populacao,
    
    Long idEstado
    ) {
    public static CidadeResponseDTO valueOf(Cidade cidade) {
        return new CidadeResponseDTO(
            cidade.getId(),
            cidade.getNome(), 
            cidade.getPopulacao(), 
            cidade.getEstado().getId());
} 
    
}
