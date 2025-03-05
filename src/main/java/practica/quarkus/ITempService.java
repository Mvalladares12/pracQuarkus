package practica.quarkus;

import java.util.List;
import java.util.Optional;

public interface ITempService {

    void agregar(Temp temp);

    List<Temp> obtenerTemp();

    boolean isEmpty();

    int obtenerMaxima();

    Optional<Temp> sacarTemp(String ciudad);
}
