package com.se356p21bt1.movie_ticket_system.repository;

import com.se356p21bt1.movie_ticket_system.model.Seat;

import java.util.ArrayList;
import java.util.List;

public class SeatRepository {
    private List<Seat> seats = new ArrayList<>();
    public SeatRepository(int numberOfSeats) {
        for (int i = 1; i <= numberOfSeats; i++) {
            seats.add(new Seat(i));
        }
    }
    public List<Seat> getSeats() {
        return seats;
    }
    public Seat getSeat(int seatNumber) {
        return seats.get(seatNumber - 1);
    }
}