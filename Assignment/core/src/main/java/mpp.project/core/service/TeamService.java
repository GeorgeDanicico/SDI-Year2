package mpp.project.core.service;

import mpp.project.core.model.Project;
import mpp.project.core.model.Team;
import mpp.project.core.exceptions.InvalidArgumentException;
import mpp.project.core.exceptions.ValidatorException;
import mpp.project.core.model.validators.TeamValidator;
import mpp.project.core.repository.ProjectRepository;
import mpp.project.core.repository.TeamRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TeamService {
    public static final Logger log = LoggerFactory.getLogger(TeamService.class);
    @Autowired
    private TeamRepository teamRepository;
    private TeamValidator validator = new TeamValidator();

    protected void validateEntity(Team entity) throws ValidatorException {
        validator.validate(entity);
    }

    public void addEntity(Team entity) throws InvalidArgumentException, ValidatorException{
        log.info("save team -- method entered");

        this.validateEntity(entity);
        teamRepository.save(entity);

        log.info("save team -- method finished");
    }

    @Transactional
    public void updateEntity(Team entity) throws ValidatorException, InvalidArgumentException{
        log.info("update team -- method entered");

        this.validateEntity(entity);
        Team updateTeam = teamRepository.findById(entity.getId()).orElseThrow(() -> new InvalidArgumentException("Invalid ID"));
        updateTeam.setName(entity.getName());
        teamRepository.save(updateTeam);

        log.info("update team -- method finished");
    }

    public void deleteEntity(Long id) throws InvalidArgumentException{
        log.info("delete team -- method entered");

        Team team = teamRepository.findById(id).orElseThrow(() -> new InvalidArgumentException("Invalid ID"));
        team.getTeamProjects().clear();
        teamRepository.deleteById(id);

        log.info("delete team -- method finished");
    }

    public Page<Team> getPagedEntities(Pageable pageable) {
        log.info("get page team -- method entered, page={}", pageable.getPageNumber());

        int currentPage = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        List<Team> teams = teamRepository.findAllTeams();

        List<Team> slicedTeams = teams.stream().skip((long)currentPage * pageSize).limit(pageSize)
                .collect(Collectors.toList());

        log.info("get page team: result={}", teams);

        return new PageImpl<>(slicedTeams, PageRequest.of(currentPage, pageSize), teams.size());
    }
}
