package ro.ubb.movieapp.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * author: radu
 */

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

    @Column(name = "description")
    private String description;

    @Column(name = "genre")
    private Genre genre;

    @OneToMany(mappedBy = "movie", fetch = FetchType.EAGER, cascade = {CascadeType.ALL},
            orphanRemoval=true)
    @JsonBackReference
    @ToString.Exclude
    Set<Rental> rentals = new HashSet<>();

}
