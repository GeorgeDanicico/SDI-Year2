package mpp.project.web.dto;

import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class AddressDto extends BaseDto{
    private String city;
    private String street;

    @Override
    public String toString() {
        return "Address ID: " + this.getId() + " | City: " + this.getCity() + " | Street: " + this.getStreet();
    }
}
