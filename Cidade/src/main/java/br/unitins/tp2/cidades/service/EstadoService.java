package br.unitins.tp2.cidades.service;

import java.util.List;

import br.unitins.tp2.cidades.dto.CidadeDTO;
import br.unitins.tp2.cidades.dto.CidadeResponseDTO;
import br.unitins.tp2.cidades.dto.EstadoDTO;
import br.unitins.tp2.cidades.dto.EstadoResponseDTO;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public interface EstadoService {

    public EstadoResponseDTO create(@Valid EstadoDTO dto);
    public void update(Long id, EstadoDTO dto);
    public void delete(Long id);
    public EstadoResponseDTO findById(Long id);
    public List<EstadoResponseDTO> findAll();
    public List<EstadoResponseDTO> findByNome(String nome);
    public List<EstadoResponseDTO> findBySigla(String sigla);
    public List<CidadeResponseDTO> findCidades(Long id);
    public Response updateListaCidades(Long id); 
    public Response updatePopulacao(Long id);
    public Response updateCapital(Long id, Long idCapital);
}
