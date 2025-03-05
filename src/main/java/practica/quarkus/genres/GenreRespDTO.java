package practica.quarkus.genres;


import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record GenreRespDTO(Long id, String name) {
}
