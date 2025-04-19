package com.se356p21bt1.movie_ticket_system.repository;

import com.se356p21bt1.movie_ticket_system.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, String> {
    List<Movie> findByTitleContainingIgnoreCase(String title);
    List<Movie> findByGenreIgnoreCase(String genre);
}
