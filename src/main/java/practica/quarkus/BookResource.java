package practica.quarkus;

import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;

import java.util.List;
import java.util.NoSuchElementException;

@Path("/books")
@Transactional
public class BookResource {

    @Inject
    private BookRepository repo;

    @GET
    public List<Book> index(@QueryParam("p") String title) {
        if (title == null) {
            return repo.listAll(Sort.by("releaseDate", Sort.Direction.Descending));
//        }else {
//            return repo.list("numPages >= ?1", numPag);
//        }
        }else {
            String filter="%"+title+"%";
            return repo.list("title ILIKE ?1 OR description ILIKE ?2", filter, filter);
        }
    }

    @POST
    @Transactional
    public Book insert(Book insertedBook) {
        repo.persist(insertedBook);
        return insertedBook;
    }

    @GET
    @Path("{id}")
    public Book retrieve(@PathParam("id") Long id){
        var book=repo.findById(id);
        if(book!=null){
            return book;
        }
        throw new NoSuchElementException("No hay libro con el id " + id);
    }

    @DELETE
    @Path("{id}")
    public void delete(@PathParam("id") Long id){
        repo.deleteById(id);
    }

    @PUT
    @Path("{id}")
    public Book update(@PathParam("id") Long id, Book ub) {
        var updatedBook=repo.findById(id);
        if(updatedBook!=null){
            updatedBook.setTitle(ub.getTitle());
            updatedBook.setReleaseDate(ub.getReleaseDate());
            updatedBook.setNumPages(ub.getNumPages());
            updatedBook.setDescription(ub.getDescription());
            repo.persist(updatedBook);
            return updatedBook;
        }else {
            throw new NoSuchElementException("No hay libro con el id " + id);
        }
    }
}
