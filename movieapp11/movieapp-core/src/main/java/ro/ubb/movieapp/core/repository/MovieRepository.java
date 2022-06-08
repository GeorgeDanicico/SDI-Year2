package ro.ubb.movieapp.core.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.ubb.movieapp.core.model.Movie;

import java.util.List;

/**
 * author: radu
 */

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    @Query("SELECT m FROM Movie m WHERE m.year <= ?1 order by m.year desc")
    @EntityGraph(value = "movieWithActors", type = EntityGraph.EntityGraphType.LOAD)
    List<Movie> findMoviesWithActorByYearLess(Integer year);
    List<Movie> findByYearLessThanEqualOrderByYearDesc(Integer year);
    List<Movie> findByYearGreaterThanEqualOrderByYearDesc(Integer year);

    @Query("SELECT m FROM Movie m WHERE m.year >= ?1 order by m.year desc")
    @EntityGraph(value = "movieWithActors", type = EntityGraph.EntityGraphType.LOAD)
    List<Movie> findMoviesWithActorByYearGreater(Integer year);

    @Query("SELECT m FROM Movie m")
    @EntityGraph(value = "movieWithActors", type = EntityGraph.EntityGraphType.LOAD)
    List<Movie> findAllMovies();
}
