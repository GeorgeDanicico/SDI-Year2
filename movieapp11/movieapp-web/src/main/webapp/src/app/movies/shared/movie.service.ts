import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Movie} from "./movie.model";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MovieService {
  private url = "http://localhost:8080/movies/"

  constructor(private httpClient: HttpClient) { }

  getMoviesByYear(year: number, before: boolean): Observable<Movie[]> {
    return this.httpClient.get<Movie[]>(`${this.url}${year}/${before}`);
  }

  getMoviesWithActorByYear(year: number, before: boolean): Observable<Movie[]> {
    return this.httpClient.get<Movie[]>(`${this.url}actors/${year}/${before}`);
  }

  deleteActor(movieId: number, actorId: number) {
    return this.httpClient.delete(`${this.url}delete/${movieId}/${actorId}`);
  }

}
