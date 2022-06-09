package ro.ubb.movieapp.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * author: radu
 */

@Entity(name = "Client")
@Table(name = "client")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Client extends BaseEntity<Long> {
    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lastname")
    private String lastName;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "address_id")
    @JsonManagedReference
    private Address address;

    @OneToMany(mappedBy = "client", fetch = FetchType.EAGER, cascade = {CascadeType.ALL},
            orphanRemoval=true)
    @JsonBackReference
    @ToString.Exclude
    Set<Rental> rentals = new HashSet<>();


}
