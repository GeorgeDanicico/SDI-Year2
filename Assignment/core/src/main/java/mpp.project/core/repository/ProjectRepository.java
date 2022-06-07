package mpp.project.core.repository;

import mpp.project.core.model.Member;
import mpp.project.core.model.Project;
import mpp.project.core.model.Team;
import mpp.project.core.model.TeamProject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT distinct p FROM Project p")
    @EntityGraph(value = "projectCreatedByMember", type = EntityGraph.EntityGraphType.LOAD)
    List<Project> findProjectsCreatedByMembers();
    

    List<Project> findByCreatedBy(Member createdBy);
}