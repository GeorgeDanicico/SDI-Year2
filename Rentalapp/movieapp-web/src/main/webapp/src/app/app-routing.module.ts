import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {MoviesComponent} from "./movies/movies.component";
import {DataimportComponent} from "./dataimport/dataimport.component";

const routes: Routes = [
  { path: 'movies', component: MoviesComponent },
  { path: 'data-import', component: DataimportComponent },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
