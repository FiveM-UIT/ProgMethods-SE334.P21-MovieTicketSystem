package com.se356p21bt1.movie_ticket_system.controller;

import com.se356p21bt1.movie_ticket_system.model.Seat;
import com.se356p21bt1.movie_ticket_system.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/seats")
public class SeatController {
    @Autowired
    private SeatService seatService;

    @GetMapping("/showtime/{showtimeId}")
    public ResponseEntity<List<Seat>> getSeatsByShowtimeId(@PathVariable String showtimeId) {
        List<Seat> seats = seatService.getSeatsByShowtimeId(showtimeId);
        return ResponseEntity.ok(seats);
    }

    @PostMapping("/reserve/{seatId}")
    public ResponseEntity<Seat> reserveSeat(@PathVariable int seatId, @RequestParam String showtimeId) {
        Seat seat = seatService.reserveSeat(seatId, showtimeId);
        if (seat != null) {
            return ResponseEntity.ok(seat);
        }
        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/cancel/{seatId}")
    public ResponseEntity<Seat> cancelSeatReservation(@PathVariable int seatId, @RequestParam String showtimeId) {
        Seat seat = seatService.cancelReservation(seatId, showtimeId);
        if (seat != null) {
            return ResponseEntity.ok(seat);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/available/{showtimeId}")
    public ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable String showtimeId) {
        List<Seat> availableSeats = seatService.getAvailableSeats(showtimeId);
        return ResponseEntity.ok(availableSeats);
    }
}