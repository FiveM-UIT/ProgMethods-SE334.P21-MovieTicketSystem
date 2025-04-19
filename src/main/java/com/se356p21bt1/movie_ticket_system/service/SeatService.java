package com.se356p21bt1.movie_ticket_system.service;

import com.se356p21bt1.movie_ticket_system.model.Seat;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class SeatService {
    private final Map<String, List<Seat>> showtimeSeats = new HashMap<>();

    public List<Seat> getSeatsByShowtimeId(String showtimeId) {
        return showtimeSeats.getOrDefault(showtimeId, new ArrayList<>());
    }

    public Seat reserveSeat(int seatId, String showtimeId) {
        List<Seat> seats = showtimeSeats.get(showtimeId);
        if (seats != null) {
            for (Seat seat : seats) {
                if (seat.getSeatNumber() == seatId && seat.isAvailable()) {
                    seat.reserve();
                    return seat;
                }
            }
        }
        return null;
    }

    public Seat cancelReservation(int seatId, String showtimeId) {
        List<Seat> seats = showtimeSeats.get(showtimeId);
        if (seats != null) {
            for (Seat seat : seats) {
                if (seat.getSeatNumber() == seatId) {
                    seat.reset();
                    return seat;
                }
            }
        }
        return null;
    }

    public List<Seat> getAvailableSeats(String showtimeId) {
        List<Seat> allSeats = showtimeSeats.get(showtimeId);
        if (allSeats == null) return new ArrayList<>();
        
        return allSeats.stream()
                .filter(Seat::isAvailable)
                .toList();
    }

    public void initializeSeats(String showtimeId, int numberOfSeats) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 1; i <= numberOfSeats; i++) {
            seats.add(new Seat(i));
        }
        showtimeSeats.put(showtimeId, seats);
    }
}