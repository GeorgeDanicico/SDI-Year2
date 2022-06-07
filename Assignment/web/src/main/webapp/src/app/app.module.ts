import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MembersComponent } from './members/members.component';
import { TeamsComponent } from './teams/teams.component';
import { ProjectsComponent } from './projects/projects.component';
import {MemberService} from "./members/shared/member.service";
import { MemberListComponent } from './members/member-list/member-list.component';
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { MemberNewComponent } from './members/member-new/member-new.component';
import { TeamListComponent } from './teams/team-list/team-list.component';
import { TeamNewComponent } from './teams/team-new/team-new.component';
import {TeamService} from "./teams/shared/team.service";
import { ProjectNewComponent } from './projects/project-new/project-new.component';
import { ProjectListComponent } from './projects/project-list/project-list.component';
import {ProjectService} from "./projects/shared/project.service";
import { NewTeamProjectComponent } from './projects/new-team-project/new-team-project.component';
import { AddressesComponent } from './addresses/addresses.component';
import { AddressListComponent } from './addresses/address-list/address-list.component';
import { AddressNewComponent } from './addresses/address-new/address-new.component';
import {AddressService} from "./addresses/shared/address.service";
import {NgxPaginationModule} from "ngx-pagination";

@NgModule({
  declarations: [
    AppComponent,
    MembersComponent,
    TeamsComponent,
    ProjectsComponent,
    MemberListComponent,
    MemberNewComponent,
    TeamListComponent,
    TeamNewComponent,
    ProjectNewComponent,
    ProjectListComponent,
    NewTeamProjectComponent,
    AddressesComponent,
    AddressListComponent,
    AddressNewComponent,
  ],
  imports: [
    FormsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    NgxPaginationModule,
  ],
  providers: [MemberService, TeamService, ProjectService, AddressService],
  bootstrap: [AppComponent]
})
export class AppModule { }
