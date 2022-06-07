package mpp.project.web.converter;

import mpp.project.core.model.Member;
import mpp.project.web.dto.MemberDto;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class MemberConverter {
    public Member convertDtoToModel(MemberDto dto) {
        var model = new Member();
        model.setId(dto.getId());
        model.setName(dto.getName());
        model.setDateOfBirth(dto.getDateOfBirth());
        model.setAddress(dto.getAddress());
        return model;
    }

    public MemberDto convertModelToDto(Member member) {
        MemberDto dto = new MemberDto(member.getName(), member.getDateOfBirth(), member.getAddress());
        dto.setId(member.getId());
        return dto;
    }

    public List<MemberDto> convertModelsToDtos(Collection<Member> models) {
        return models.stream()
                .map(model -> convertModelToDto(model))
                .collect(Collectors.toList());
    }
}
