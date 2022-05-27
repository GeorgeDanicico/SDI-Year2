package mpp.project.core.repository;

import mpp.project.core.model.Team;
import mpp.project.core.model.TeamProject;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("SELECT t from Team t")
    @EntityGraph(value = "teamsOnProject", type = EntityGraph.EntityGraphType.LOAD)
    List<Team> findAllTeams();
//    @Query(value = "SELECT tp.* FROM teamproject tp ON t.id = tp.team_id WHERE tp.project_id = :projectId"
//            , nativeQuery = true)
//    List<TeamProject> getTeamProjects(@Param("projectId") Long projectId, Pageable pageable);
}