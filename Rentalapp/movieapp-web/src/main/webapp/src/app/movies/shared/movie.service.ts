import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Movie, MovieDto} from "./movie.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private url = "http://localhost:8080/movies/"

  constructor(private httpClient: HttpClient) { }

  getAllMovies(params: any): Observable<MovieDto> {
    return this.httpClient
      .get<MovieDto>(this.url, { params });
  }

}
