package mpp.project.web.dto;

import lombok.*;
import mpp.project.core.model.Member;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class ProjectDto extends BaseDto{
    private String name;
    private String description;
    private Member createdBy;

    @Override
    public String toString() {
        return "Project ID: " + this.getId() + " | Name: " + this.getName() + " | Description: " +
                this.getDescription() + " | Created by: " + this.getCreatedBy();
    }

}
