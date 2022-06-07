package mpp.project.core.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NamedEntityGraphs({
        @NamedEntityGraph(name = "teamProjectPair",
        attributeNodes = {
                @NamedAttributeNode("team"),
                @NamedAttributeNode(value = "project", subgraph = "createdBy-subgraph")
        },
        subgraphs = {
                @NamedSubgraph(name = "createdBy-subgraph", attributeNodes = @NamedAttributeNode(value = "createdBy", subgraph = "address-subgraph")),
                @NamedSubgraph(name = "address-subgraph", attributeNodes = @NamedAttributeNode("address"))
        })
})
@Entity(name = "TeamProject")
@Table(name = "teamproject")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TeamProject implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="project_id")
    @JsonManagedReference
    private Project project;

    @ManyToOne(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name="team_id")
    @JsonManagedReference
    private Team team;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof TeamProject)) return false;

        TeamProject tp = (TeamProject) o;
        return this.getTeam().getId().equals(tp.getTeam().getId()) &&
                this.getProject().getId().equals(tp.getProject().getId());
    }
}