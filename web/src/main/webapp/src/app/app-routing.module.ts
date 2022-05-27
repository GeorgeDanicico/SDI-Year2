import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MembersComponent} from "./members/members.component";
import {MemberNewComponent} from "./members/member-new/member-new.component";
import {TeamsComponent} from "./teams/teams.component";
import {TeamNewComponent} from "./teams/team-new/team-new.component";
import {ProjectsComponent} from "./projects/projects.component";
import {ProjectNewComponent} from "./projects/project-new/project-new.component";
import {NewTeamProjectComponent} from "./projects/new-team-project/new-team-project.component";
import {AddressesComponent} from "./addresses/addresses.component";
import {AddressNewComponent} from "./addresses/address-new/address-new.component";

const routes: Routes = [
  { path: 'members', component: MembersComponent },
  { path: 'member/new', component: MemberNewComponent },

  { path: 'teams', component: TeamsComponent },
  { path: 'team/new', component: TeamNewComponent },

  { path: 'projects', component: ProjectsComponent },
  { path: 'project/new', component: ProjectNewComponent },

  { path: 'project/teams/new/:id', component: NewTeamProjectComponent },

  { path: 'addresses', component: AddressesComponent },
  { path: 'address/new', component: AddressNewComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
