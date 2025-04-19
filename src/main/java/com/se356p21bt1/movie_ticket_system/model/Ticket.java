package com.se356p21bt1.movie_ticket_system.model;

public class Ticket {
    private Movie movie;
    private Seat seat;

    public Ticket(Movie movie, Seat seat) {
        this.movie = movie;
        this.seat = seat;
    }

    public Movie getMovie() {
        return movie;
    }
    public Seat getSeat() {
        return seat;
    }
}