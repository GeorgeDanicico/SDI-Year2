package mpp.project.web.converter;

import mpp.project.core.model.Address;
import mpp.project.web.dto.AddressDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class AddressConverter {
    public Address convertDtoToModel(AddressDto dto) {
        var model = Address.builder()
                .id(dto.getId())
                .city(dto.getCity())
                .street(dto.getStreet())
                .build();
        return model;
    }

    public AddressDto convertModelToDto(Address address) {
        AddressDto dto = new AddressDto(address.getCity(), address.getStreet());
        dto.setId(address.getId());
        return dto;
    }

    public List<AddressDto> convertModelsToDtos(Collection<Address> models) {
        return models.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toList());
    }
}
