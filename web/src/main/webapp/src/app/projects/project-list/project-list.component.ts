import {Component, OnInit} from '@angular/core';
import { Project } from "../shared/project.model";
import { ProjectService } from "../shared/project.service";
import { Router } from "@angular/router";
import {Member} from "../../members/shared/member.model";
import {Team} from "../../teams/shared/team.model";

@Component({
  selector: 'app-project-list',
  templateUrl: './project-list.component.html',
  styleUrls: ['./project-list.component.css']
})
export class ProjectListComponent implements OnInit {
  errorMessage: string;
  projects: Project[];
  selectedProject: Project;
  page = 1;
  count = 0;
  showProjectTeams = false;
  projectTeams: Team[];
  selectProjectTeam: Team;
  assignedTeamsPage = 1;
  assignedTeamsCount = 0;

  constructor(private projectService: ProjectService,
              private router: Router) {
  }

  ngOnInit(): void {
    this.getProjects();
  }

  getProjectTeams() {
    const params = { page: this.assignedTeamsPage - 1}

    this.projectService.getProjectTeams(this.selectedProject.id, params).subscribe(
      teamsDto => {
        this.projectTeams = teamsDto.teams;
        this.assignedTeamsCount = teamsDto.totalItems;
      }
    );
  }

  getProjects() {
    const params = { page: this.page - 1 }

    this.projectService.getProjects(params)
      .subscribe(projectsDto => {
        this.projects = projectsDto.projects;
        this.count = projectsDto.totalItems;
        },
        error => this.errorMessage = error);
  }

  onSelect(project: Project): void {
    this.selectedProject = project;
  }

  onTeamSelect(team: Team): void {
    this.selectProjectTeam = team;
  }

  onTeamDelete(): void {
    this.selectProjectTeam = null;
    this.getProjectTeams()
  }

  onDelete(): void {
    this.getProjects();
    this.selectedProject = null;
  }

  handlePageChange(event: any) {
    this.page = event;
    this.getProjects();
  }

  handleAssignedTeamsPageChange(event: any) {
    this.assignedTeamsPage = event;
    this.getProjectTeams();
  }

  addTeamToProject(): void {
    this.router.navigate(['project/teams/new', this.selectedProject.id]);
  }

  deleteTeamFromProject(): void {
    this.projectService.deleteTeamFromProject(this.selectedProject.id, this.selectProjectTeam.id).subscribe(_ => this.onDelete());
  }

  viewAssignedTeams(): void {
    this.showProjectTeams = true;

    this.getProjectTeams()
  }

  hideAssignedTeams(): void {
    this.showProjectTeams = false;
  }

  deleteProject(): void {
    this.projectService.delete(this.selectedProject.id).subscribe(_ => this.onDelete());
  }

  goToAdd(): void {
    this.router.navigate(['project/new']);
  }

  save(): void {
    this.projectService.update(this.selectedProject)
      .subscribe(_ => this.getProjects());
  }

  cancelSelectedProject(): void {
    this.selectedProject = null;
    this.getProjects();
  }

  cancelTeamProject(): void {
    this.selectProjectTeam = null;
  }

}
