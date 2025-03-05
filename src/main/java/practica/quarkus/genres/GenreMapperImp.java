package practica.quarkus.genres;


import jakarta.enterprise.context.RequestScoped;

@RequestScoped
public class GenreMapperImp implements GenreMapper {
    @Override
    public Genre fromCreate(CreateGenreDTO dto) {
        var genre = new Genre();
        genre.setName(dto.name());
        return genre;
    }

    @Override
    public void update(UpdateGenreDTO dto, Genre genre) {
        genre.setName(dto.name());
    }

    @Override
    public GenreRespDTO present(Genre g) {
        return new GenreRespDTO(g.getId(), g.getName());
    }
}
