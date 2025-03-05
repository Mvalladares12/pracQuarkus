package practica.quarkus;

import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class TempService implements ITempService {

    private List<Temp> nuevas=new ArrayList<>();

    @Override
    public void agregar(Temp temp) {
        nuevas.add(temp);
    }

    @Override
    public List<Temp> obtenerTemp() {
        return Collections.unmodifiableList(nuevas);
    }


    public void borrarTemp(int i) {
        nuevas.remove(i);
    }

    @Override
    public boolean isEmpty() {
        return nuevas.isEmpty();
    }

    @Override
    public int obtenerMaxima() {
        return nuevas.stream().mapToInt(Temp::getMaxima).max().orElse(0);
    }

    @Override
    public Optional<Temp> sacarTemp(String ciudad) {
        return nuevas.stream()
                .filter(t -> t.getCiudad().equals(ciudad))
                .findAny();
    }


}
