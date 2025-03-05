package practica.quarkus.genres;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.awt.print.Pageable;
import java.util.NoSuchElementException;

@ApplicationScoped
public class GenreRepository implements PanacheRepository<Genre> {

    //@Inject
    private GenreMapper gm;

    @Inject
    public GenreRepository(GenreMapper gm) {
        this.gm = gm;
    }

    public PanacheQuery<Genre> findPage(int page) {
        Page p=new Page(page-1,5);
        var query=findAll(Sort.descending("creationDate"));
        query.page(p);
        return query;
    }

    //Función que se encarga de actualizar un genero, se implementa en GenreResource
    public GenreRespDTO update(Long id, UpdateGenreDTO genre) {
        var updatedGenre = findById(id);
        if (updatedGenre != null) {
            //updatedGenre.setName(genre.getName());
            gm.update(genre, updatedGenre);
            persist(updatedGenre);
            return gm.present(updatedGenre);
        }else {
            throw new NoSuchElementException("No existe genero con el id " + id);
        }
    }

    public GenreRespDTO encontrarPorId(Long id) {
        return  findByIdOptional(id)
                .map(gm::present)
                .orElseThrow(() -> new NoSuchElementException("Genero con id " + id + " no encontrado"));
    }
}
