package br.unitins.tp2.cidades.dto;

import java.util.List;
import java.util.Optional;

import br.unitins.tp2.cidades.model.Cidade;
import br.unitins.tp2.cidades.model.Estado;

public record EstadoResponseDTO (
    Long id,
    String nome,
    String sigla,
    Long populacao,
    Optional<CidadeResponseDTO> Capital,
    List<CidadeResponseDTO> cidades
) {
    public static EstadoResponseDTO valueOf(Estado estado) {
        List<CidadeResponseDTO> lista = estado.getCidades() != null?
                                            estado.getCidades().stream()
                                            .map(CidadeResponseDTO::valueOf)
                                            .toList(): null;
        Optional<CidadeResponseDTO> capitalDTO = Optional.ofNullable(
            estado.getCapital() != null ? CidadeResponseDTO.valueOf(estado.getCapital()) : null
        );
        return new EstadoResponseDTO(
            estado.getId(), 
            estado.getNome(), 
            estado.getSigla(),
            estado.getPopulacao(),
            capitalDTO,
            lista);
    }
    
}
