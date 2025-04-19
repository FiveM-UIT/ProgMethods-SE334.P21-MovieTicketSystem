package com.se356p21bt1.movie_ticket_system.service;

import com.se356p21bt1.movie_ticket_system.model.Movie;
import com.se356p21bt1.movie_ticket_system.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public String addMovie(Movie movie) {
        Movie savedMovie = movieRepository.save(movie);
        return savedMovie.getId();
    }

    public Optional<Movie> getMovieById(String id) {
        return movieRepository.findById(id);
    }

    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    public List<Movie> searchMoviesByTitle(String title) {
        return movieRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Movie> searchMoviesByGenre(String genre) {
        return movieRepository.findByGenreIgnoreCase(genre);
    }

    public boolean updateMovie(String id, Movie movie) {
        if (movieRepository.existsById(id)) {
            movie.setId(id);
            movieRepository.save(movie);
            return true;
        }
        return false;
    }

    public boolean deleteMovie(String id) {
        if (movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return true;
        }
        return false;
    }
}