package br.unitins.tp2.cidades.service;

import java.util.List;

import br.unitins.tp2.cidades.dto.CidadeDTO;
import br.unitins.tp2.cidades.dto.CidadeResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public interface CidadeService {

    public CidadeResponseDTO create(@Valid CidadeDTO dto);
    public void update(Long id, CidadeDTO dto);
    public void delete(Long id);
    public CidadeResponseDTO findById(Long id);
    public List<CidadeResponseDTO> findAll();
    public List<CidadeResponseDTO> findByNome(String nome);
    public Response updatePopulacao(Long id, Long populacao);
    public List<CidadeResponseDTO> findByEstado(Long idEstado);
}
