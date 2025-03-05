package practica.quarkus.genres;

import jakarta.validation.constraints.NotBlank;

public record UpdateGenreDTO(
        @NotBlank
        String name
) {
}
