package br.unitins.tp2.cidades.dto.dtoPatch;

import jakarta.validation.constraints.NotNull;

public record PopCidadeDTO (
    @NotNull(message = "A cidade não pode ser nula")
    Long idCidade,
    @NotNull(message = "A população não pode ser nula ou vazia")
    Long populacao
){}
