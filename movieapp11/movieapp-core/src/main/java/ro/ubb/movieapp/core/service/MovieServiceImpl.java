package ro.ubb.movieapp.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.ubb.movieapp.core.model.Actor;
import ro.ubb.movieapp.core.model.Movie;
import ro.ubb.movieapp.core.repository.MovieRepository;

import java.util.List;

/**
 * author: radu
 */

@Service
public class MovieServiceImpl implements MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Override
    public List<Movie> getMoviesByYear(int year, boolean lessThan) {
        if (lessThan)
            return this.movieRepository.findByYearLessThanEqualOrderByYearDesc(year);

        return this.movieRepository.findByYearGreaterThanEqualOrderByYearDesc(year);
    }

    @Override
    public List<Movie> getMoviesWithActorsByYear(int year, boolean lessThan) {
        if (lessThan)
            return this.movieRepository.findMoviesWithActorByYearLess(year);

        return this.movieRepository.findMoviesWithActorByYearGreater(year);
    }

    @Override
    @Transactional
    public void deleteActor(Long movieId, Long actorId) {
        Movie movie = movieRepository.findById(movieId).orElseThrow(() -> new RuntimeException("Invalid movie ID"));
        Actor actor = movie.getActors().stream().filter(actor1 -> actor1.getId().equals(actorId)).findFirst().orElseThrow();

        movie.getActors().remove(actor);
        actor.setMovie(null);

//        Actor actor = movie.getActors()
    }
}
