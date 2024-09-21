package br.unitins.tp2.cidades.service;

import java.util.List;
import java.util.Optional;

import br.unitins.tp2.cidades.dto.CidadeDTO;
import br.unitins.tp2.cidades.dto.CidadeResponseDTO;
import br.unitins.tp2.cidades.dto.EstadoDTO;
import br.unitins.tp2.cidades.dto.EstadoResponseDTO;
import br.unitins.tp2.cidades.model.Cidade;
import br.unitins.tp2.cidades.model.Estado;
import br.unitins.tp2.cidades.repository.CidadeRepository;
import br.unitins.tp2.cidades.repository.EstadoRepository;
import br.unitins.tp2.cidades.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class EstadoServiceImpl implements EstadoService {

    @Inject
    public EstadoRepository estadoRepository;

    @Inject
    public CidadeRepository cidadeRepository;

    @Override
    @Transactional
    public EstadoResponseDTO create(@Valid EstadoDTO dto) {
        validarNomeEstado(dto.nome());

        Estado estado = new Estado();
        estado.setNome(dto.nome());
        estado.setSigla(dto.sigla());
        estadoRepository.persist(estado);
        updatePopulacao(estado.getId());
        return EstadoResponseDTO.valueOf(estado);
    }

    public void validarNomeEstado(String nome) {
        Estado estado = estadoRepository.findByNomeCompleto(nome);
        if (estado != null)
            throw  new ValidationException("nome", "O estado de nome "+nome+" já existe.");
    }
    

    @Override
    @Transactional
    public void update(Long id, EstadoDTO dto) {
        Estado estadoBanco =  estadoRepository.findById(id);
        estadoBanco.setNome(dto.nome());
        estadoBanco.setSigla(dto.sigla());
        updateListaCidades(id);
        updatePopulacao(id);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        estadoRepository.deleteById(id);
    }

    @Override
    public EstadoResponseDTO findById(Long id) {
        Estado estado = estadoRepository.findById(id);
        if (estado != null)
            return EstadoResponseDTO.valueOf(estado);
        return null;
    }

    @Override
    public List<EstadoResponseDTO> findAll() {
        return estadoRepository
        .listAll()
        .stream()
        .map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EstadoResponseDTO> findByNome(String nome) {
        return estadoRepository.findByNome(nome).stream()
        .map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<EstadoResponseDTO> findBySigla(String sigla) {
        return estadoRepository.findBySigla(sigla).stream()
        .map(e -> EstadoResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<CidadeResponseDTO> findCidades(Long id) {
        return estadoRepository.findCidades(id).stream()
        .map(c -> CidadeResponseDTO.valueOf(c)).toList();
    }

    @Override
    @Transactional
    public Response updateListaCidades(Long id) {
        Estado estado = estadoRepository.findById(id);
        if (estado == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        estado.setCidades(cidadeRepository.findByEstado(estado.getId()));
        return Response.ok().build();
    }

    @Override
    @Transactional
    public Response updatePopulacao(Long id) {
        Estado estado = estadoRepository.findById(id);
        if (estado == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        if (estado.getCidades() != null)
            estado.setPopulacao(cidadeRepository.findByEstado(estado.getId()).stream().mapToLong(Cidade::getPopulacao).sum());
        return Response.ok().build();
    }
    
    @Override
    @Transactional
    public Response updateCapital(Long id, Long idCapital) {
        Estado estado = estadoRepository.findById(id);
        if (estado == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        estado.setCapital(cidadeRepository.findById(idCapital));
        return Response.ok().build();
    }
}
