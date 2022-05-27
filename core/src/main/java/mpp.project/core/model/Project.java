package mpp.project.core.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
@NamedEntityGraphs({
        @NamedEntityGraph(name = "projectCreatedByMember",
        attributeNodes = {
                @NamedAttributeNode(value = "createdBy", subgraph = "createdBy-subgraph"),
                @NamedAttributeNode(value = "teamProjects", subgraph = "teams-subgraph"),
        },
        subgraphs = {
                @NamedSubgraph(name = "createdBy-subgraph", attributeNodes = @NamedAttributeNode("address")),
                @NamedSubgraph(name = "teams-subgraph", attributeNodes = @NamedAttributeNode("team")),
        })
})
@Entity(name = "Project")
@Table(name = "project")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Project implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "createdby", nullable = false)
    private Member createdBy;

    @OneToMany(mappedBy = "project", fetch = FetchType.LAZY, cascade = {CascadeType.ALL},
            orphanRemoval=true)
    @JsonBackReference
    @ToString.Exclude
    Set<TeamProject> teamProjects = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Project)) return false;

        Project p = (Project) o;
        return p.getId().equals(this.getId());
    }
}