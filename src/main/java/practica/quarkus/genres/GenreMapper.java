package practica.quarkus.genres;

public interface GenreMapper {

    Genre fromCreate(CreateGenreDTO dto);

    void update(UpdateGenreDTO dto, Genre genre);

    GenreRespDTO present(Genre g);
}
