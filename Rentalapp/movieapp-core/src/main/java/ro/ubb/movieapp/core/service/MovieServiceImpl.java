package ro.ubb.movieapp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movieapp.core.model.Client;
import ro.ubb.movieapp.core.model.Movie;
import ro.ubb.movieapp.core.repository.MovieRepository;

import java.util.List;
import java.util.stream.Collectors;

/**
 * author: radu
 */

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public Page<Movie> getPagedEntities(Pageable pageable) {

        return movieRepository.findAll(pageable);
    }

    public void uploadMovies(List<Movie> movies) {
        movieRepository.saveAll(movies);
    }

}
