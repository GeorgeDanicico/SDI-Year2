import {Component, OnInit} from '@angular/core';
import { Team } from "../shared/team.model";
import { TeamService } from "../shared/team.service";
import { Router } from "@angular/router";
import {Member} from "../../members/shared/member.model";
import {filter} from "rxjs";

@Component({
  selector: 'app-team-list',
  templateUrl: './team-list.component.html',
  styleUrls: ['./team-list.component.css']
})
export class TeamListComponent implements OnInit {
  errorMessage: string;
  teams: Team[];
  selectedTeam: Team;
  filterCondition: string;
  page = 1;
  count = 0;

  constructor(private teamService: TeamService,
              private router: Router) {
    this.filterCondition = "";
  }


  filterTeams(newName: string): Team[] {
    return this.teams.filter((team) => team.name.indexOf(newName) !== -1);
  }

  ngOnInit(): void {
    this.getTeams();
  }

  getTeams() {
    const params = { page: this.page - 1 };

    this.teamService.getTeams(params)
      .subscribe(teamsDto => {
        this.teams = teamsDto.teams;
        this.count = teamsDto.totalItems;
      })
  }

  handlePageChange(event: any) {
    this.page = event;
    this.getTeams();
  }

  onSelect(team: Team): void {
    this.selectedTeam = team;
  }

  onDelete(): void {
    this.getTeams();
    this.selectedTeam = null;
  }

  save(): void {
    this.teamService.update(this.selectedTeam)
      .subscribe(_ => this.getTeams());
  }

  cancel(): void {
    this.selectedTeam = null;
    this.getTeams();
  }

  deleteTeam(): void {
    this.teamService.delete(this.selectedTeam.id).subscribe(_ => this.onDelete());
  }

  goToAdd(): void {
    this.router.navigate(['team/new']);
  }

}
