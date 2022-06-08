import { Component, OnInit } from '@angular/core';
import {MovieService} from "./shared/movie.service";
import {Actor, Movie} from "./shared/movie.model";

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {
  year: number;
  before = false;
  after = false;
  movies: Movie[];

  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
  }

  onBeforeChange(): void {
    this.before = true;
    this.after = false;
    this.getMovies();
  }

  onAfterChange(): void {
    this.before = false;
    this.after = true;
    this.getMovies();
  }

  onClick() {
    this.movieService.getMoviesWithActorByYear(this.year, this.before).subscribe(movies => this.movies = movies)
  }

  getMovies() {
    this.movieService.getMoviesByYear(this.year, this.before).subscribe(movies => this.movies = movies)
  }

  onDelete(movieId: number, actorId: number) {
    this.movieService.deleteActor(movieId, actorId).subscribe(_ => {
      console.log('deleted');
      this.onClick()
    });

  }

}
