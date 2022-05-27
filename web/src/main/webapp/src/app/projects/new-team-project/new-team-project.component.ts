import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Location} from "@angular/common";
import {ProjectService} from "../shared/project.service";

@Component({
  selector: 'app-new-team-project',
  templateUrl: './new-team-project.component.html',
  styleUrls: ['./new-team-project.component.css']
})

export class NewTeamProjectComponent implements OnInit {
  projectId: number;

  constructor(private projectService: ProjectService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.projectId = parseInt(params['id']);
    })
  }

  goBack(): void {
    this.location.back();
  }

  saveTeamToProject(id: string): void {

    if (!Number.isNaN(id)) {
      this.projectService.addTeamToProject({id: null, projectId: this.projectId, teamId: parseInt(id)})
        .subscribe(_ => this.goBack(),
          error => console.log(error));
    }
  }

}
