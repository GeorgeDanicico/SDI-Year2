import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { MoviesComponent } from './movies/movies.component';
import {MovieService} from "./movies/shared/movie.service";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {NgxPaginationModule} from "ngx-pagination";
import { DataimportComponent } from './dataimport/dataimport.component';
import {DataimportService} from "./dataimport/shared/dataimport.service";

@NgModule({
  declarations: [
    AppComponent,
    MoviesComponent,
    DataimportComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        FormsModule,
        HttpClientModule,
        NgxPaginationModule,
    ],
  providers: [MovieService, DataimportService],
  bootstrap: [AppComponent]
})
export class AppModule { }
