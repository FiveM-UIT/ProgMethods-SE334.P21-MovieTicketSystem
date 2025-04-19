package com.se356p21bt1.movie_ticket_system.service;

import com.se356p21bt1.movie_ticket_system.model.ShowTime;
import com.se356p21bt1.movie_ticket_system.model.Movie;
import com.se356p21bt1.movie_ticket_system.repository.ShowTimeRepository;
import com.se356p21bt1.movie_ticket_system.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ShowTimeService {
    @Autowired
    private ShowTimeRepository showTimeRepository;
    
    @Autowired
    private MovieRepository movieRepository;

    public ShowTime createShowTime(ShowTime showTime) {
        if (showTime.getMovie() != null) {
            return showTimeRepository.save(showTime);
        }
        return null;
    }

    public List<ShowTime> getAllShowTimes() {
        return showTimeRepository.findAll();
    }

    public Optional<ShowTime> getShowTimeById(String id) {
        return showTimeRepository.findById(id);
    }

    public List<ShowTime> getShowTimesByMovie(Movie movie) {
        return showTimeRepository.findByMovie(movie);
    }

    public List<ShowTime> getShowTimesByRoomId(String roomId) {
        return showTimeRepository.findByRoomId(roomId);
    }

    public ShowTime updateShowTime(String id, ShowTime updatedShowTime) {
        Optional<ShowTime> existingShowTime = showTimeRepository.findById(id);
        if (existingShowTime.isPresent()) {
            ShowTime showTime = existingShowTime.get();
            showTime.setMovie(updatedShowTime.getMovie());
            showTime.setStartTime(updatedShowTime.getStartTime());
            showTime.setEndTime(updatedShowTime.getEndTime());
            showTime.setTicketPrice(updatedShowTime.getTicketPrice());
            showTime.setTotalSeats(updatedShowTime.getTotalSeats());
            showTime.setAvailableSeats(updatedShowTime.getAvailableSeats());
            showTime.setRoomId(updatedShowTime.getRoomId());
            return showTimeRepository.save(showTime);
        }
        return null;
    }

    public boolean deleteShowTime(String id) {
        if (showTimeRepository.existsById(id)) {
            showTimeRepository.deleteById(id);
            return true;
        }
        return false;
    }
    
    public List<ShowTime> getActiveShowTimes() {
        LocalDateTime now = LocalDateTime.now();
        return showTimeRepository.findAll().stream()
                .filter(ShowTime::isShowTimeActive)
                .toList();
    }
}