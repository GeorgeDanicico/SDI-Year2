package mpp.project.web.controller;

import mpp.project.core.exceptions.InvalidArgumentException;
import mpp.project.core.exceptions.ValidatorException;
import mpp.project.core.model.Member;
import mpp.project.core.model.Project;
import mpp.project.core.model.Team;
import mpp.project.web.dto.TeamProjectDto;
import mpp.project.web.converter.TeamConverter;
import mpp.project.web.dto.TeamsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import mpp.project.core.service.ProjectService;
import mpp.project.web.converter.ProjectConverter;
import mpp.project.web.dto.ProjectDto;
import mpp.project.web.dto.ProjectsDto;

import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private ProjectConverter projectConverter;
    @Autowired
    private TeamConverter teamConverter;


    @GetMapping( "/")
    ResponseEntity<Map<String, Object>> getAllProjects(
            @RequestParam(defaultValue = "0") int page
    ) {
        //todo: logs
        try {
            Pageable pageable = PageRequest.of(page, 4);
            Map<String, Object> response = new HashMap<>();

            Page<Project> pageProjects = projectService.getPagedEntities(pageable);
            List<Project> projects = pageProjects.getContent();
            response.put("projects", projects);
            response.put("currentPage", pageProjects.getNumber());
            response.put("totalItems", pageProjects.getTotalElements());
            response.put("totalPages", pageProjects.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping("/")
    ResponseEntity<?> addProject(@RequestBody ProjectDto ProjectDto) {
        // TODO: Log parameters

        try {
            Member createdBy = projectService.getMember(ProjectDto.getCreatedBy().getId());
            var project = projectConverter.convertDtoToModel(ProjectDto);
            project.setCreatedBy(createdBy);
            projectService.addEntity(project);

            // TODO: Log result model
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping( "/{id}")
    ResponseEntity<?> updateProject(@PathVariable Long id,
                                 @RequestBody ProjectDto dto) {

        try {
            Member createdBy = projectService.getMember(dto.getCreatedBy().getId());
            var project = projectConverter.convertDtoToModel(dto);
            project.setCreatedBy(createdBy);
            projectService.updateEntity(project);

            // TODO: Log result model
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteProject(@PathVariable Long id) {
        try {
            projectService.deleteEntity(id);

            // TODO: Log result model
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/teams")
    ResponseEntity<?> addTeamToProject(@RequestBody TeamProjectDto teamProjectDto) {
        // TODO: Log parameters
        try {
            var teamId = teamProjectDto.getTeamId();
            var projectId = teamProjectDto.getProjectId();
            projectService.addTeamToProject(projectId, teamId);

            // TODO: Log result model
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(e.getMessage());
        }
    }

    @DeleteMapping( "/teams/{projectId}/{teamId}")
    ResponseEntity<?> deleteTeamFromProject(@PathVariable Long projectId, @PathVariable Long teamId) {
        try {
            projectService.deleteTeamFromProject(projectId, teamId);

            // TODO: Log result model
            return ResponseEntity.ok(HttpStatus.OK);
        } catch (InvalidArgumentException | ValidatorException e) {
            return ResponseEntity.ok(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/teams/{id}")
    ResponseEntity<Map<String, Object>> getAllProjectTeams(
            @PathVariable Long id,
            @RequestParam(defaultValue = "0") int page
    ) {
        //todo: logs

        try {
            Pageable pageable = PageRequest.of(page, 4);
            Map<String, Object> response = new HashMap<>();

            Page<Team> pageTeams = projectService.getPagedProjectTeams(id, pageable);
            List<Team> projectTeams = pageTeams.getContent();
            System.out.println(projectTeams);

            response.put("teams", pageTeams.getContent());
            response.put("currentPage", pageTeams.getNumber());
            response.put("totalItems", pageTeams.getTotalElements());
            response.put("totalPages", pageTeams.getTotalPages());

            System.out.println("arrived here");

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
