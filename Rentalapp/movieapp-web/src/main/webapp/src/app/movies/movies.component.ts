import { Component, OnInit } from '@angular/core';
import {MovieService} from "./shared/movie.service";
import {Actor, Movie} from "./shared/movie.model";

@Component({
  selector: 'app-movies',
  templateUrl: './movies.component.html',
  styleUrls: ['./movies.component.css']
})
export class MoviesComponent implements OnInit {

  movies: Movie[];
  page = 1;
  count = 0;

  constructor(private movieService: MovieService) { }

  ngOnInit(): void {
    this.getMovies();
  }

  getMovies() {
    const params = { page: this.page - 1 };

    this.movieService.getAllMovies(params).subscribe(moviesdto => {
      this.movies = moviesdto.movies;
      this.count = moviesdto.totalItems;
    })
  }

  handlePageChange(event: any) {
    this.page = event;
    this.getMovies();
  }
}
