package com.se356p21bt1.movie_ticket_system.repository;

import com.se356p21bt1.movie_ticket_system.model.ShowTime;
import com.se356p21bt1.movie_ticket_system.model.Movie;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class ShowTimeRepository {
    private final Map<String, ShowTime> showTimes = new HashMap<>();

    public ShowTime save(ShowTime showTime) {
        if (showTime.getId() == null) {
            showTime.setId(UUID.randomUUID().toString());
        }
        showTimes.put(showTime.getId(), showTime);
        return showTime;
    }

    public List<ShowTime> findAll() {
        return new ArrayList<>(showTimes.values());
    }

    public Optional<ShowTime> findById(String id) {
        return Optional.ofNullable(showTimes.get(id));
    }

    public List<ShowTime> findByMovie(Movie movie) {
        return showTimes.values().stream()
                .filter(showTime -> showTime.getMovie().equals(movie))
                .collect(Collectors.toList());
    }

    public List<ShowTime> findByRoomId(String roomId) {
        return showTimes.values().stream()
                .filter(showTime -> showTime.getRoomId().equals(roomId))
                .collect(Collectors.toList());
    }

    public List<ShowTime> findByStartTimeAfter(LocalDateTime time) {
        return showTimes.values().stream()
                .filter(showTime -> showTime.getStartTime().isAfter(time))
                .collect(Collectors.toList());
    }

    public List<ShowTime> findByStartTimeBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return showTimes.values().stream()
                .filter(showTime -> showTime.getStartTime().isAfter(startTime) 
                        && showTime.getStartTime().isBefore(endTime))
                .collect(Collectors.toList());
    }

    public List<ShowTime> findByMovieAndStartTimeAfter(Movie movie, LocalDateTime time) {
        return showTimes.values().stream()
                .filter(showTime -> showTime.getMovie().equals(movie) 
                        && showTime.getStartTime().isAfter(time))
                .collect(Collectors.toList());
    }

    public List<ShowTime> findByAvailableSeatsGreaterThan(int seats) {
        return showTimes.values().stream()
                .filter(showTime -> showTime.getAvailableSeats() > seats)
                .collect(Collectors.toList());
    }

    public boolean existsById(String id) {
        return showTimes.containsKey(id);
    }

    public void deleteById(String id) {
        showTimes.remove(id);
    }

    public void deleteAll() {
        showTimes.clear();
    }
}