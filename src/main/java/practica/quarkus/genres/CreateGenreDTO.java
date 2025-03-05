package practica.quarkus.genres;

import jakarta.validation.constraints.NotBlank;

public record CreateGenreDTO(

        @NotBlank
        String name
) {
}
