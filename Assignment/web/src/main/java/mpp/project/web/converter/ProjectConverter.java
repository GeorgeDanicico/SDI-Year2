package mpp.project.web.converter;

import mpp.project.core.model.Member;
import mpp.project.core.model.Project;
import mpp.project.core.repository.MemberRepository;
import mpp.project.web.dto.ProjectDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProjectConverter {

    public Project convertDtoToModel(ProjectDto dto) {
        var model = new Project();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setDescription(dto.getDescription());
        model.setCreatedBy(dto.getCreatedBy());
        return model;
    }

    public ProjectDto convertModelToDto(Project project) {
        ProjectDto dto = new ProjectDto(project.getName(), project.getDescription(), project.getCreatedBy());
        dto.setId(project.getId());
        return dto;
    }

    public Set<ProjectDto> convertModelsToDtos(Collection<Project> models) {
        return models.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toSet());
    }
}
