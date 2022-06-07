package mpp.project.web.dto;

import lombok.*;
import mpp.project.core.model.Project;
import mpp.project.core.model.Team;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TeamProjectDto extends BaseDto{
    private Long projectId;
    private Long teamId;
}
