import {Component, Input, OnInit} from '@angular/core';
import {Project} from "../shared/project.model";
import {ProjectService} from "../shared/project.service";
import {ActivatedRoute, Params} from "@angular/router";
import {Location} from "@angular/common";
import {switchMap} from "rxjs";

@Component({
  selector: 'app-project-new',
  templateUrl: './project-new.component.html',
  styleUrls: ['./project-new.component.css']
})
export class ProjectNewComponent implements OnInit {

  constructor(private projectService: ProjectService,
              private route: ActivatedRoute,
              private location: Location) {
  }

  ngOnInit(): void {

  }

  goBack(): void {
    this.location.back();
  }

  saveProject(name: string, description: string, id: string): void {
    console.log("this is called");

    if (!Number.isNaN(id)) {
      const createdById = parseInt(id);
      this.projectService.save({id: null, name, description, createdBy: {
          id: createdById,
          name: null,
          dateOfBirth: null,
          address: null,
        }})
        .subscribe(_ => this.goBack(),
          error => console.log(error));
    }
  }

}
