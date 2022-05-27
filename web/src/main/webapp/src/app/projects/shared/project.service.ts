import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Project, ProjectDto} from "./project.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";
import {Team, TeamDto} from "../../teams/shared/team.model";


@Injectable()
export class ProjectService {
  private projectsUrl = 'http://localhost:8080/api/projects/';

  constructor(private httpClient: HttpClient) {
  }

  getProjects(params: any): Observable<ProjectDto> {
    return this.httpClient
      .get<ProjectDto>(this.projectsUrl, { params });
  }

  getProjectTeams(projectId: number, params: any): Observable<TeamDto> {
    const url = `${this.projectsUrl}teams/${projectId}`;

    return this.httpClient
      .get<TeamDto>(url, { params });
  }

  save(project: Project) {
    return this.httpClient.post<Project>(this.projectsUrl, project);
  }

  delete(projectId: number) {
    const url = `${this.projectsUrl}${projectId}`;
    return this.httpClient.delete(url);
  }

  update(project: any): Observable<Project> {
    const url = `${this.projectsUrl}${project.id}`;
    return this.httpClient
      .put<any>(url, project);
  }

  addTeamToProject(teamProject: any): Observable<any> {
    const url = `${this.projectsUrl}teams`;

    return this.httpClient.post(url, teamProject);
  }

  deleteTeamFromProject(projectId: number, teamId: number) {
    const url = `${this.projectsUrl}/teams/${projectId}/${teamId}`;

    return this.httpClient.delete(url);
  }

}
