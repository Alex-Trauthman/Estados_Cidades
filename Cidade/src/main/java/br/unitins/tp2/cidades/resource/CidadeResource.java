package br.unitins.tp2.cidades.resource;

import java.util.List;

import br.unitins.tp2.cidades.dto.CidadeDTO;
import br.unitins.tp2.cidades.dto.dtoPatch.PopCidadeDTO;
import br.unitins.tp2.cidades.service.CidadeService;
import jakarta.inject.Inject;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/cidades")
public class CidadeResource {
    @Inject
    public CidadeService cidadeService;

    
    @GET
    @Path("/{id}")
    public Response buscarPeloId(@PathParam("id") Long id) {
        return Response.ok(cidadeService.findById(id)).build();
    }

    @GET
    public Response findAll() {
        return Response.ok(cidadeService.findAll()).build();
    }

    @GET
    @Path("/search/nome/{nome}")
    public Response findByNome(@PathParam("nome") String nome) {
        return Response.ok(cidadeService.findByNome(nome)).build();
    }

    @POST
    public Response create(CidadeDTO dto) {
        return Response.status(Status.CREATED).entity(cidadeService.create(dto)).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long id, CidadeDTO dto) {
        cidadeService.update(id, dto);
        return Response.status(Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Long id) {
        cidadeService.delete(id);
        return Response.status(Status.NO_CONTENT).build();       
    }

    @GET
    @Path("/search/cidadeByEstado/{idEstado}")
    public Response findByEstado(@PathParam("idEstado") Long idEstado) {
        return Response.ok(cidadeService.findByEstado(idEstado)).build();
    }
    @PATCH
    @Path("/populacao")
    public Response updatePopulacao(PopCidadeDTO dto){
        cidadeService.updatePopulacao(dto.idCidade(),dto.populacao());
        return Response.status(Status.NO_CONTENT).build();
    }
    
}
