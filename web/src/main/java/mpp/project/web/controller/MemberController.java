package mpp.project.web.controller;

import mpp.project.core.exceptions.InvalidArgumentException;
import mpp.project.core.exceptions.ValidatorException;
import mpp.project.core.model.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mpp.project.core.model.Member;
import mpp.project.core.service.MemberService;
import mpp.project.web.converter.MemberConverter;
import mpp.project.web.dto.MemberDto;
import mpp.project.web.dto.MembersDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/members")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private MemberConverter memberConverter;

    @GetMapping("/")
    ResponseEntity<Map<String, Object>> getAllMembers(
            @RequestParam(defaultValue = "0") int page
    ) {
        //todo: logs
        try {
            Pageable pageable = PageRequest.of(page, 4);
            Map<String, Object> response = new HashMap<>();

            Page<Member> pageMembers = memberService.getPagedEntities(pageable);
            List<Member> members = pageMembers.getContent();
            response.put("members", members);
            response.put("currentPage", pageMembers.getNumber());
            response.put("totalItems", pageMembers.getTotalElements());
            response.put("totalPages", pageMembers.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping(value="/sorted")
    ResponseEntity<Map<String, Object>> getAllMembersSorted(
            @RequestParam(defaultValue = "0") int page
    ) {
        try {
            Pageable pageable = PageRequest.of(page, 4);
            Map<String, Object> response = new HashMap<>();

            Page<Member> pageMembers = memberService.getPagedSortedEntities(pageable);
            List<Member> members = pageMembers.getContent();
            response.put("members", members);
            response.put("currentPage", pageMembers.getNumber());
            response.put("totalItems", pageMembers.getTotalElements());
            response.put("totalPages", pageMembers.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping( "/")
    ResponseEntity<?> addMember(@RequestBody MemberDto MemberDto) {
        // TODO: Log parameters

        try {
            var member = memberConverter.convertDtoToModel(MemberDto);

            memberService.addEntity(member);

            // TODO: Log result model
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateMember(@PathVariable Long id,
                             @RequestBody MemberDto dto) {

        try {
            memberService.updateEntity(memberConverter.convertDtoToModel(dto));

            // TODO: Log result model
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteMember(@PathVariable Long id) {
        try {
            memberService.deleteEntity(id);

            // TODO: Log result model
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

}
