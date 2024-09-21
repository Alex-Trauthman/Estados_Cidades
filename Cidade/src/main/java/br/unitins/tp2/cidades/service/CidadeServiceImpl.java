package br.unitins.tp2.cidades.service;

import java.util.List;

import br.unitins.tp2.cidades.dto.CidadeDTO;
import br.unitins.tp2.cidades.dto.CidadeResponseDTO;
import br.unitins.tp2.cidades.model.Cidade;
import br.unitins.tp2.cidades.repository.CidadeRepository;
import br.unitins.tp2.cidades.repository.EstadoRepository;
import br.unitins.tp2.cidades.validation.ValidationException;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.core.Response;

@ApplicationScoped
public class CidadeServiceImpl implements CidadeService {

    @Inject
    public CidadeRepository cidadeRepository;

    @Inject
    public EstadoRepository estadoRepository;

    @Inject
    public EstadoServiceImpl estadoService;
    
    @Override
    @Transactional
    public CidadeResponseDTO create(@Valid CidadeDTO dto) {
        validarNomeCidade(dto.nome(), dto.estadoId());
        validarEstado(dto.estadoId());
        Cidade cidade = new Cidade();
        cidade.setNome(dto.nome());
        cidade.setPopulacao(dto.populacao());
        cidade.setEstado(estadoRepository.findById(dto.estadoId()));
        cidadeRepository.persist(cidade);
        updateOnEstado(dto.estadoId());
        
        return CidadeResponseDTO.valueOf(cidade);
    }

    public void validarNomeCidade(String nome, Long idEstado) {
        Cidade cidade = cidadeRepository.findByNomeCompleto(nome);
        if (cidade != null && cidade.getEstado().getId() == idEstado)
            throw  new ValidationException("nome", "O nome '"+nome+"' já existe nesse estado.");
    }
    public void validarEstado(Long idEstado){
        if(estadoRepository.findById(idEstado)==null){
            throw new ValidationException("estado", "O estado de id"+idEstado+" não está cadastrado");
        }
    }
    

    @Override
    @Transactional
    public void update(Long id, CidadeDTO dto) {
        Cidade cidadeBanco =  cidadeRepository.findById(id);
        Long idEstado = cidadeBanco.getEstado().getId();
        validarNomeCidade(dto.nome(), dto.estadoId());
        cidadeBanco.setEstado(estadoRepository.findById(dto.estadoId()));
        cidadeBanco.setNome(dto.nome());
        cidadeBanco.setPopulacao(dto.populacao());
        updateOnEstado(dto.estadoId());
        updateOnEstado(idEstado);
    }

    @Override
    @Transactional
    public void delete(Long id) {
        Long idEstado = cidadeRepository.findById(id).getEstado().getId();
        cidadeRepository.deleteById(id);
        updateOnEstado(idEstado);
    }

    @Override
    public CidadeResponseDTO findById(Long id) {
        Cidade cidade = cidadeRepository.findById(id);
        if (cidade != null)
            return CidadeResponseDTO.valueOf(cidade);
        return null;
    }

    @Override
    public List<CidadeResponseDTO> findAll() {
        return cidadeRepository
        .listAll()
        .stream()
        .map(e -> CidadeResponseDTO.valueOf(e)).toList();
    }

    @Override
    public List<CidadeResponseDTO> findByNome(String nome) {
        return cidadeRepository.findByName(nome).stream()
        .map(e -> CidadeResponseDTO.valueOf(e)).toList();
    }

    @Override
    @Transactional
    public Response updatePopulacao(Long id, Long populacao) {
        Cidade cidade = cidadeRepository.findById(id);
        if (cidade == null)
            return Response.status(Response.Status.NOT_FOUND).build();
        cidade.setPopulacao(populacao);
        updateOnEstado(cidade.getEstado().getId());
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @Override
    public List<CidadeResponseDTO> findByEstado(Long idEstado) {
        return cidadeRepository.findByEstado(idEstado).stream()
        .map(e -> CidadeResponseDTO.valueOf(e)).toList();
    }
    public void updateOnEstado(Long idEstado){
        estadoService.updateListaCidades(idEstado);
        estadoService.updatePopulacao(idEstado);
    }
    
}
