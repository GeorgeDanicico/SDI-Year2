package ro.ubb.movieapp.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * author: radu
 */

@Entity(name = "Rental")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Table(name = "rental")
public class Rental extends BaseEntity<Long> {

    @Column(name = "rentedDate")
    private Date rentedDate;

    @Column(name = "dueDate")
    private Date dueDate;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name="client_id")
    @JsonManagedReference
    private Client client;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinColumn(name="movie_id")
    @JsonManagedReference
    private Movie movie;

//    @Override
//    public boolean equals(Object o) {
//        if (!(o instanceof TeamProject)) return false;
//
//        TeamProject tp = (TeamProject) o;
//        return this.getTeam().getId().equals(tp.getTeam().getId()) &&
//                this.getProject().getId().equals(tp.getProject().getId());
//    }

}
