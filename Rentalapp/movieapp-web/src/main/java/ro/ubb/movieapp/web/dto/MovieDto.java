package ro.ubb.movieapp.web.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ro.ubb.movieapp.core.model.Client;
import ro.ubb.movieapp.core.model.Movie;

import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
public class MovieDto extends BaseDto{
    private String title;
    private int year;
    private Set<Client> actors;

//    public void encode(Movie movie) {
//        this.setId(movie.getId());
//        this.title = movie.getTitle();
//        this.year = movie.getYear();
//        actors = movie.getActors();
//    }
}
