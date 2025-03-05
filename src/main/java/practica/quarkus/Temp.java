package practica.quarkus;

import java.util.Objects;

public class Temp {

    private String ciudad;

    private int minima;

    private int maxima;

    public Temp(String ciudad, int minima, int maxima) {
        this.ciudad = ciudad;
        this.minima = minima;
        this.maxima = maxima;
    }

    public Temp() {
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public int getMinima() {
        return minima;
    }

    public void setMinima(int minima) {
        this.minima = minima;
    }

    public int getMaxima() {
        return maxima;
    }

    public void setMaxima(int maxima) {
        this.maxima = maxima;
    }

    @Override
    public String toString() {
        return "Temp{" +
                "ciudad='" + ciudad + '\'' +
                ", minima=" + minima +
                ", maxima=" + maxima +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Temp temp = (Temp) o;
        return minima == temp.minima && maxima == temp.maxima && Objects.equals(ciudad, temp.ciudad);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ciudad, minima, maxima);
    }
}
