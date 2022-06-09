package ro.ubb.movieapp.web.controller;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.movieapp.core.model.Movie;
import ro.ubb.movieapp.core.service.MovieServiceImpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/movies")
public class MovieController {
    @Autowired
    private MovieServiceImpl movieService;

    @GetMapping("/")
    ResponseEntity<Map<String, Object>> getAllMovies(
            @RequestParam(defaultValue = "0") int page
    ) {
        //todo: logs
        try {
            Pageable pageable = PageRequest.of(page, 4);
            Map<String, Object> response = new HashMap<>();

            Page<Movie> pageMovies = movieService.getPagedEntities(pageable);
            List<Movie> movies = pageMovies.getContent();
            response.put("movies", movies);
            response.put("currentPage", pageMovies.getNumber());
            response.put("totalItems", pageMovies.getTotalElements());
            response.put("totalPages", pageMovies.getTotalPages());

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    
}
