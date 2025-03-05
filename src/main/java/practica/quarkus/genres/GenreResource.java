package practica.quarkus.genres;


import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import java.util.List;
import java.util.NoSuchElementException;

@Path("/genre")
public class GenreResource {

    @Inject
    private GenreRepository gr;

    @GET
    public PaginatedResponse<Genre> list(@QueryParam("page") @DefaultValue("1") int page){
        Page p=new Page(page-1,5);
        var query=gr.findAll(Sort.descending("creationDate")).page(p);
        return new PaginatedResponse<>(query);
    }

    @POST
    @Transactional
    public Genre insert(Genre insertedGenre) {
        gr.persist(insertedGenre);
        return insertedGenre;
    }

    @GET
    @Path("{id}")
    public Genre get(@PathParam("id") Long id) {
        return gr
                .findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("Genero con id " + id + " no encontrado"));
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
    public Genre update(@PathParam("id") Long id, Genre updatedG) {
        var updatedGenre = gr.findById(id);
        if (updatedGenre != null) {
            updatedGenre.setName(updatedG.getName());
            gr.persist(updatedGenre);
            return updatedGenre;
        }else {
            throw new NoSuchElementException("No existe genero con el id " + id);
        }
    }
}
