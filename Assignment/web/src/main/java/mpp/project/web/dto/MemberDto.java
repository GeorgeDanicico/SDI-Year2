package mpp.project.web.dto;

import lombok.*;
import mpp.project.core.model.Address;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class MemberDto extends BaseDto{
    private String name;
    private Date dateOfBirth;
    private Address address;

    @Override
    public String toString() {
        return "Member ID: " + this.getId() + " | Name: " + this.getName() + " | Birth date: " + this.getDateOfBirth();
    }
}
