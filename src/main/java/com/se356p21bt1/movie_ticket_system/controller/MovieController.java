package com.se356p21bt1.movie_ticket_system.controller;

import com.se356p21bt1.movie_ticket_system.model.Movie;
import com.se356p21bt1.movie_ticket_system.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {
    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        String id = movieService.addMovie(movie);
        return ResponseEntity.ok(movie);
    }

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieService.getAllMovies();
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable String id) {
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search/title")
    public ResponseEntity<List<Movie>> searchMoviesByTitle(@RequestParam String title) {
        List<Movie> movies = movieService.searchMoviesByTitle(title);
        return ResponseEntity.ok(movies);
    }

    @GetMapping("/search/genre")
    public ResponseEntity<List<Movie>> searchMoviesByGenre(@RequestParam String genre) {
        List<Movie> movies = movieService.searchMoviesByGenre(genre);
        return ResponseEntity.ok(movies);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable String id, @RequestBody Movie updatedMovie) {
        boolean updated = movieService.updateMovie(id, updatedMovie);
        if (updated) {
            return movieService.getMovieById(id)
                    .map(ResponseEntity::ok)
                    .orElse(ResponseEntity.notFound().build());
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable String id) {
        boolean deleted = movieService.deleteMovie(id);
        if (deleted) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}