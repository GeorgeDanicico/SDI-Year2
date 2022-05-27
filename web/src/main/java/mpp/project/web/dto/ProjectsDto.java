package mpp.project.web.dto;

import lombok.*;

import java.util.Date;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ProjectsDto {
    private Set<ProjectDto> projects;
}

