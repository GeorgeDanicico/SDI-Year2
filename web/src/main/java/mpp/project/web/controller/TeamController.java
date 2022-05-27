package mpp.project.web.controller;

import mpp.project.core.exceptions.InvalidArgumentException;
import mpp.project.core.exceptions.ValidatorException;
import mpp.project.core.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mpp.project.core.model.Team;
import mpp.project.core.service.TeamService;
import mpp.project.web.converter.TeamConverter;
import mpp.project.web.dto.TeamDto;
import mpp.project.web.dto.TeamsDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private TeamConverter teamConverter;

    @GetMapping(value = "/")
    ResponseEntity<Map<String, Object>> getAllTeams(
            @RequestParam(defaultValue = "0") int page
    ) {
        //todo: logs
        try {
            Pageable pageable = PageRequest.of(page, 4);
            Map<String, Object> response = new HashMap<>();

            Page<Team> pageTeams = teamService.getPagedEntities(pageable);
            List<Team> teams = pageTeams.getContent();
            response.put("teams", teams);
            response.put("currentPage", pageTeams.getNumber());
            response.put("totalItems", pageTeams.getTotalElements());
            response.put("totalPages", pageTeams.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping("/")
    ResponseEntity<?> addTeam(@RequestBody TeamDto TeamDto) {
        var team = teamConverter.convertDtoToModel(TeamDto);

        try {
            teamService.addEntity(team);

            // TODO: Log result model
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping(value = "/{id}")
    ResponseEntity<?> updateTeam(@PathVariable Long id,
                                   @RequestBody TeamDto dto) {

        teamService.updateEntity(teamConverter.convertDtoToModel(dto));

        try {
            teamService.updateEntity(teamConverter.convertDtoToModel(dto));

            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/{id}")
    ResponseEntity<?> deleteTeam(@PathVariable Long id) {
        try {
            teamService.deleteEntity(id);

            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

}
