package ro.ubb.movieapp.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.movieapp.core.model.Movie;
import ro.ubb.movieapp.core.service.MovieServiceImpl;
import ro.ubb.movieapp.web.dto.MovieDto;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieServiceImpl movieService;

    @GetMapping("/{year}/{before}")
    public List<Movie> getAllMoviesByYear(@PathVariable Integer year,
                                          @PathVariable Boolean before) {

        return this.movieService.getMoviesByYear(year, before);
    }

    @GetMapping("/actors/{year}/{before}")
    List<MovieDto> getMoviesWithActors(@PathVariable Integer year,
                                          @PathVariable Boolean before) {
        List<MovieDto> list = this.movieService.getMoviesWithActorsByYear(year, before)
                .stream()
                .map((movie) -> {
                    MovieDto dto = new MovieDto();
                    dto.encode(movie);

                    return dto;
                })
                .collect(Collectors.toList());
        return list;
    }

    @DeleteMapping("/delete/{movieId}/{actorId}")
    ResponseEntity<?> getMoviesWithActors(@PathVariable Long movieId,
                                       @PathVariable Long actorId) {
        try {
            this.movieService.deleteActor(movieId, actorId);
            return ResponseEntity.ok(HttpStatus.OK);

        } catch (Exception e) {
            return ResponseEntity.ok("Bad request");
        }
    }
}
