package br.unitins.tp2.cidades.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CidadeDTO(
    @NotBlank(message = "O nome não pode ser nulo ou vazio")
    String nome,
    @NotNull(message = "A população não pode ser nula ou vazia")
    Long populacao,
    @NotNull(message = "O estado não pode ser nulo")
    Long estadoId
) {
} 