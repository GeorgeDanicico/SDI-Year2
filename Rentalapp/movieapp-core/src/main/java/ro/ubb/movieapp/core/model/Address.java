package ro.ubb.movieapp.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * author: radu
 */

@Entity(name = "Address")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@Table(name = "addres")
public class Address extends BaseEntity<Long> {
    @Column(name = "title")
    private String title;

    @Column(name = "county")
    private String county;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "ssn")
    private String ssn;

    @OneToOne(mappedBy = "address", fetch = FetchType.EAGER)
    @JsonBackReference
    private Client client;

}
