package ro.ubb.movieapp.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * author: radu
 */

@NamedEntityGraphs({
        @NamedEntityGraph(name = "movieWithActors",
                attributeNodes = @NamedAttributeNode(value = "actors"))
})
@Entity(name = "Movie")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Table(name = "movie")
public class Movie extends BaseEntity<Long> {
    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private int year;

    //actors
    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY, cascade = { CascadeType.ALL},
        orphanRemoval = true)
    @JsonBackReference
    @ToString.Exclude
    Set<Actor> actors = new HashSet<>();

}
