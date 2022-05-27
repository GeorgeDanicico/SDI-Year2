import {Injectable} from '@angular/core';

import {HttpClient} from "@angular/common/http";

import {Team, TeamDto} from "./team.model";

import {Observable} from "rxjs";
import {map} from "rxjs/operators";


@Injectable()
export class TeamService {
  private teamsUrl = 'http://localhost:8080/api/teams/';

  constructor(private httpClient: HttpClient) {
  }

  getTeams(params: any): Observable<TeamDto> {
    return this.httpClient
      .get<TeamDto>(this.teamsUrl, { params });
  }

  getTeam(params: any, id: number): Observable<Team> {
    return this.getTeams(params)
      .pipe(
        map(teamsdto => teamsdto.teams.find(team => team.id === id))
      );
  }

  save(team: Team) {
    return this.httpClient.post<Team>(this.teamsUrl, team);
  }

  delete(teamId: number) {
    const url = `${this.teamsUrl}${teamId}`;
    return this.httpClient.delete(url);
  }

  update(team: Team): Observable<Team> {
    const url = `${this.teamsUrl}${team.id}`;
    return this.httpClient
      .put<Team>(url, team);
  }

}
