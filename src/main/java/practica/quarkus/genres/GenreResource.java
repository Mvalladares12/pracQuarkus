package practica.quarkus.genres;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@Path("/genre")
public class GenreResource {

    private GenreMapper gm;

    private GenreRepository gr;

    private GenreValidator validator;

    @Inject
    public GenreResource(GenreRepository gr, GenreMapper gm, GenreValidator validator) {
        this.gr = gr;
        this.gm = gm;
        this.validator = validator;
    }

    @GET
    public PaginatedResponse<GenreRespDTO> list(
            @QueryParam("page") @DefaultValue("1") int page,
            @QueryParam("q") String q) {

        /*var query= gr.findPage(page);

        if (q != null) {
            var nameLike="%"+q+"%";
            query.filter("name.like", Parameters.with("name",nameLike));
        }

        return new PaginatedResponse<>(query);*/
        PanacheQuery<Genre> query = gr.findPage(page);
        PanacheQuery<GenreRespDTO> pres=query.project(GenreRespDTO.class);

        return new PaginatedResponse<GenreRespDTO>(pres);
    }

    @POST
    @Transactional
    public Response insert(CreateGenreDTO genre) {
        var errors = this.validator.validate(genre);
        if (errors.isPresent()) {
            var msg = errors.get();
            return Response.status(400).entity(msg).build();
        }
        var entity=gm.fromCreate(genre);
        gr.persist(entity);
        var rep=gm.present(entity);
        return Response.created(URI.create("/genre/" + entity.getId())).entity(rep).build();
    }

    @GET
    @Path("{id}")
    public GenreRespDTO encontrarPorId(@PathParam("id") Long id) {
        return gr.encontrarPorId(id);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        gr.deleteById(id);
    }

    @PUT
    @Path("{id}")
    @Transactional
    public GenreRespDTO update(@PathParam("id") Long id,@Valid UpdateGenreDTO genre) {
        //Toma la funcion desde GenreRepository
        return gr.update(id, genre);
    }
}
