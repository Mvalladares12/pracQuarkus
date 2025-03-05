package practica.quarkus.genres;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validation;
import jakarta.validation.Validator;

import java.util.Optional;

@ApplicationScoped
public class GenreValidator {

    Validator validator;

    @Inject
    public GenreValidator(Validator validator) {
        this.validator = validator;
    }

    public Optional<String> validate(CreateGenreDTO createGenre) {
        var errors = validator.validate(createGenre);
        if (errors.isEmpty()) {
            return Optional.empty();
        }
        return Optional.of(errors.stream().findFirst().get().getMessage());
    }
}
