package mpp.project.core.service;

import mpp.project.core.model.Member;
import mpp.project.core.model.Project;
import mpp.project.core.model.Team;
import mpp.project.core.model.TeamProject;
import mpp.project.core.exceptions.InvalidArgumentException;
import mpp.project.core.exceptions.ValidatorException;
import mpp.project.core.model.validators.ProjectValidator;
import mpp.project.core.repository.*;
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
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MemberRepository memberRepository;
    public static final Logger log = LoggerFactory.getLogger(TeamService.class);
    private ProjectValidator projectValidator = new ProjectValidator();


    public void addEntity(Project entity) throws ValidatorException, InvalidArgumentException {
        log.info("add project -- method entered");

        projectValidator.validate(entity);
        memberRepository.findById(entity.getCreatedBy().getId()).orElseThrow(() -> new InvalidArgumentException("Invalid ID"));
        projectRepository.save(entity);

        log.info("add project -- method finished");
    }

    @Transactional
    public void updateEntity(Project entity) throws ValidatorException, InvalidArgumentException {
        log.info("update project -- method entered");

        projectValidator.validate(entity);
        memberRepository.findById(entity.getCreatedBy().getId()).orElseThrow(() -> new InvalidArgumentException("Invalid ID"));

        Project updateProject = projectRepository.findById(entity.getId()).orElseThrow();
        updateProject.setDescription(entity.getDescription());
        updateProject.setName(entity.getName());
        updateProject.setCreatedBy(entity.getCreatedBy());
        projectRepository.save(updateProject);

        log.info("update project -- method finished");
    }

    public void deleteEntity(Long id) {
        log.info("delete project -- method entered");

        projectRepository.findById(id).orElseThrow(() -> new InvalidArgumentException("Invalid ID"));
//        teamProjectRepository.findByProjectId(id)
//                .forEach((tp) -> teamProjectRepository.deleteById(tp.getId()));

        projectRepository.deleteById(id);

        log.info("delete project -- method finished");
    }

    public Page<Project> getPagedEntities(Pageable pageable) {
        log.info("get page projects -- method entered, page={}", pageable.getPageNumber());

        int currentPage = pageable.getPageNumber();
        int pageSize = pageable.getPageSize();
        List<Project> projects = projectRepository.findProjectsCreatedByMembers();

        List<Project> slicedProjects = projects.stream().skip((long)currentPage * pageSize).limit(pageSize)
                .collect(Collectors.toList());

        log.info("get page projects: result={}", projects);

        return new PageImpl<>(slicedProjects, PageRequest.of(currentPage, pageSize), projects.size());
    }

    public Member getMember(Long id) throws InvalidArgumentException{
        return memberRepository.findById(id).orElseThrow(() -> new InvalidArgumentException("Invalid ID"));
    }

//    public List<Long> getProjectTeamIds(Long projectId) throws InvalidArgumentException{
//        Project project = projectRepository.findById(projectId).orElseThrow(() -> new InvalidArgumentException("Invalid ID"));
//
//        List<TeamProject> sortedTeamProjects = project.getTeamProjects().stream()
//                .sorted(Comparator.comparing(TeamProject::getId))
//                .collect(Collectors.toList());
//
//        return sortedTeamProjects
//                .stream()
//                .map(tp -> tp.getTeam().getId())
//                .collect(Collectors.toList());
//    }

    @Transactional
    public Page<Team> getPagedProjectTeams(Long projectId, Pageable pageable) throws InvalidArgumentException {
        log.info("get paged project teams -- method entered, page={}", pageable.getPageNumber());

        int pageSize = pageable.getPageSize();
        int currentPage = pageable.getPageNumber();

         Project project = projectRepository.findProjectsCreatedByMembers().stream()
                .filter(p -> p.getId().equals(projectId))
                .findFirst().orElseThrow(() -> new InvalidArgumentException("Invalid project id"));

         List<Team> teams = project.getTeamProjects().stream()
                 .map(TeamProject::getTeam)
                 .collect(Collectors.toList());

        List<Team> slicedTeams = teams.stream().skip((long) currentPage * pageSize)
                .limit(4).collect(Collectors.toList());

        log.info("get page project teams: result={}", slicedTeams);

        return new PageImpl<>(slicedTeams, PageRequest.of(currentPage, pageSize), teams.size());
    }

    @Transactional
    public void addTeamToProject(Long projectId, Long teamId) throws InvalidArgumentException{
        log.info("add team to project -- method entered");

        System.out.println(projectId +", " + teamId);
        Project project = projectRepository.findById(projectId).orElseThrow(() -> new InvalidArgumentException("Invalid Project ID"));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new InvalidArgumentException("Invalid Team ID"));

        TeamProject tp = TeamProject.builder()
                .project(project)
                .team(team).build();

        project.getTeamProjects().add(tp);
        team.getTeamProjects().add(tp);

        log.info("add team to project -- method finished");
    }

    @Transactional
    public void deleteTeamFromProject(Long projectId, Long teamId){
        log.info("delete team from project -- method entered");

        Project project = projectRepository.findById(projectId).orElseThrow(() -> new InvalidArgumentException("Invalid Project ID"));
        Team team = teamRepository.findById(teamId).orElseThrow(() -> new InvalidArgumentException("Invalid Team ID"));

        TeamProject tp1 = TeamProject.builder()
                .project(project)
                .team(team).build();

        TeamProject tp = project.getTeamProjects().stream()
                .filter(teamProject -> teamProject.equals(tp1))
                .findFirst().orElseThrow(() -> new InvalidArgumentException("Invalid id's"));


        project.getTeamProjects().remove(tp);
        team.getTeamProjects().remove(tp);
        tp.setProject(null);
        tp.setTeam(null);

        System.out.println(project.getTeamProjects());

        log.info("delete team from project -- method finished");
    }

}
