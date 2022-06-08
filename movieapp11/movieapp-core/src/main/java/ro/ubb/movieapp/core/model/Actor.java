package ro.ubb.movieapp.core.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

/**
 * author: radu
 */

@Entity(name = "Actor")
@Table(name = "actor")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Actor extends BaseEntity<Long> {
    @Column(name = "name")
    private String name;

    @Column(name = "rating")
    private int rating;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Movie movie;


}
