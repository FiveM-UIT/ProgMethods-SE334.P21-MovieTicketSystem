package com.se356p21bt1.movie_ticket_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;
    private String title;
    private String genre;
    private double ticketPrice;
    private String screeningTime;
    private int numberOfSeats;

    public Movie(String title, String genre, double ticketPrice, String screeningTime, int numberOfSeats) {
        this.title = title;
        this.genre = genre;
        this.ticketPrice = ticketPrice;
        this.screeningTime = screeningTime;
        this.numberOfSeats = numberOfSeats;
    }

    public String getTitle() {
        return title;
    }
    public String getGenre() {
        return genre;
    }
    public double getTicketPrice() {
        return ticketPrice;
    }
    public String getScreeningTime() {
        return screeningTime;
    }
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    
    // Add getter and setter for id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}