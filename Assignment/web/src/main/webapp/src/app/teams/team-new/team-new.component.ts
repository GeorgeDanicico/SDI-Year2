import {Component, Input, OnInit} from '@angular/core';
import {Team} from "../shared/team.model";
import {TeamService} from "../shared/team.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs";

@Component({
  selector: 'app-team-new',
  templateUrl: './team-new.component.html',
  styleUrls: ['./team-new.component.css']
})
export class TeamNewComponent implements OnInit {

  constructor(private teamService: TeamService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {

  }

  goBack(): void {
    this.location.back();
  }

  saveTeam(name: string): void {
    this.teamService.save({ id: null, name})
      .subscribe(_ => this.goBack(),
        error => console.log(error));
  }

}
