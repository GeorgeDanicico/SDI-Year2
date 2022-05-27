package mpp.project.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "teamsOnProject",
                attributeNodes = {
                        @NamedAttributeNode("teamProjects")
                })
})
@Entity(name = "Team")
@Table(name ="team")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
@ToString(callSuper = true)
public class Team implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "createdby")
    private Long createdBy;

    @OneToMany(mappedBy = "team", fetch = FetchType.LAZY, cascade = {CascadeType.ALL},
            orphanRemoval=true)
    @JsonBackReference
    @ToString.Exclude
    Set<TeamProject> teamProjects = new HashSet<>();

    public Team(Long teamId) {
        this.id = teamId;
    }
}