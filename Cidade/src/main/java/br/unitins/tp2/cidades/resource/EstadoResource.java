package br.unitins.tp2.cidades.resource;

import java.util.List;

import br.unitins.tp2.cidades.dto.CidadeDTO;
import br.unitins.tp2.cidades.dto.EstadoDTO;
import br.unitins.tp2.cidades.dto.dtoPatch.CapEstadoDTO;
import br.unitins.tp2.cidades.service.EstadoService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Path("/estados")
public class EstadoResource {
    
    @Inject
    public EstadoService estadoService;

    @GET
    @Path("/{id}")
    public Response buscarPeloId(@PathParam("id") Long id) {
        return Response.ok(estadoService.findById(id)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(estadoService.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(estadoService.findByNome(nome)).build();
    }

    @GET
    @Path("/search/sigla/{sigla}")
    public Response findBySigla(@PathParam("sigla") String sigla) {
        return Response.ok(estadoService.findBySigla(sigla)).build();
    }

    @POST
    public Response create(EstadoDTO dto) {
        return Response.status(Status.CREATED).entity(estadoService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, EstadoDTO dto) {
        estadoService.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        estadoService.delete(id);
        return Response.status(Status.NO_CONTENT).build();
            
    }

    @GET
    @Path("/{id}/cidades")
    public Response findCidades(@PathParam("id") Long id) {
        return Response.ok(estadoService.findCidades(id)).build();
    }

    @PATCH
    @Path("capital")
    public Response updateCapital(CapEstadoDTO dto) {
        return estadoService.updateCapital(dto.idEstado(), dto.idCapital());
    }
}
