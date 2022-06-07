package mpp.project.web.converter;

import mpp.project.core.model.Team;
import mpp.project.web.dto.TeamDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TeamConverter {
    public Team convertDtoToModel(TeamDto dto) {
        var model = new Team();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setCreatedBy(dto.getCreatedBy());
        return model;
    }

    public TeamDto convertModelToDto(Team team) {
        TeamDto dto = new TeamDto(team.getName(), team.getCreatedBy());
        dto.setId(team.getId());
        return dto;
    }

    public Set<TeamDto> convertModelsToDtos(Collection<Team> models) {
        return models.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toSet());
    }
}
