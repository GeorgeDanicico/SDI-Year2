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
public interface MovieRepository extends IRepository<Movie, Long> {

}
