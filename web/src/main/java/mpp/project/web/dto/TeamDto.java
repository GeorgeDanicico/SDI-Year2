package mpp.project.web.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class TeamDto extends BaseDto{
    private String name;
    private Long createdBy;
}
