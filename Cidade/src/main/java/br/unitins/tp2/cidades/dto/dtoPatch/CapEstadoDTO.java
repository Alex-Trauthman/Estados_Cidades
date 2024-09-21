package br.unitins.tp2.cidades.dto.dtoPatch;

import jakarta.validation.constraints.NotNull;

public record CapEstadoDTO(
    @NotNull(message= "O id do estado deve ser informado")
    Long idEstado,
    @NotNull(message = "O id da capital deve ser informado")
    Long idCapital
) {
}