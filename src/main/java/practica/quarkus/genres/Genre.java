package practica.quarkus.genres;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import org.hibernate.annotations.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@FilterDef(name = "name.like", parameters = @ParamDef(name = "name", type = String.class))
@Filter(name = "name.like", condition = "LOWER(name) LIKE LOWER(:name)")
public class Genre {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    @CreationTimestamp
    //@JsonIgnore
    private LocalDateTime creationDate;

    @UpdateTimestamp
    //@JsonIgnore
    private LocalDateTime modDate;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getModDate() {
        return modDate;
    }

    public void setModDate(LocalDateTime modDate) {
        this.modDate = modDate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Genre genre = (Genre) o;
        return Objects.equals(id, genre.id) && Objects.equals(name, genre.name) && Objects.equals(creationDate, genre.creationDate) && Objects.equals(modDate, genre.modDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, creationDate, modDate);
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", creationDate=" + creationDate +
                ", modDate=" + modDate +
                '}';
    }
}
