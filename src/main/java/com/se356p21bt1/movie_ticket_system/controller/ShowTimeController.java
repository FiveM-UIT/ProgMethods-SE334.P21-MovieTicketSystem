package com.se356p21bt1.movie_ticket_system.controller;

import com.se356p21bt1.movie_ticket_system.model.Movie;
import com.se356p21bt1.movie_ticket_system.model.ShowTime;
import com.se356p21bt1.movie_ticket_system.service.ShowTimeService;
import com.se356p21bt1.movie_ticket_system.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/showtimes")
public class ShowTimeController {
    @Autowired
    private ShowTimeService showTimeService;

    @Autowired
    private MovieService movieService;

    @PostMapping
    public ResponseEntity<ShowTime> createShowTime(@RequestBody ShowTime showTime) {
        ShowTime createdShowTime = showTimeService.createShowTime(showTime);
        return ResponseEntity.ok(createdShowTime);
    }

    @GetMapping
    public ResponseEntity<List<ShowTime>> getAllShowTimes() {
        List<ShowTime> showTimes = showTimeService.getAllShowTimes();
        return ResponseEntity.ok(showTimes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShowTime> getShowTimeById(@PathVariable String id) {
        return showTimeService.getShowTimeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/movie/{movieId}")
    public ResponseEntity<List<ShowTime>> getShowTimesByMovieId(@PathVariable String movieId) {
        Optional<Movie> movie = movieService.getMovieById(movieId);
        if (movie.isPresent()) {
            List<ShowTime> showTimes = showTimeService.getShowTimesByMovie(movie.get());
            return ResponseEntity.ok(showTimes);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/room/{roomId}")
    public ResponseEntity<List<ShowTime>> getShowTimesByRoomId(@PathVariable String roomId) {
        List<ShowTime> showTimes = showTimeService.getShowTimesByRoomId(roomId);
        return ResponseEntity.ok(showTimes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShowTime> updateShowTime(@PathVariable String id, @RequestBody ShowTime updatedShowTime) {
        ShowTime showTime = showTimeService.updateShowTime(id, updatedShowTime);
        if (showTime != null) {
            return ResponseEntity.ok(showTime);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteShowTime(@PathVariable String id) {
        if (showTimeService.deleteShowTime(id)) {
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}