package com.se356p21bt1.movie_ticket_system.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ShowTime {
    private String id;
    private Movie movie;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private double ticketPrice;
    private int totalSeats;
    private int availableSeats;
    private String roomId;
    
    // Default constructor
    public ShowTime() {
    }

    // Full constructor
    public ShowTime(String id, Movie movie, LocalDateTime startTime, LocalDateTime endTime, 
                   String roomId) {
        this.id = id;
        this.movie = movie;
        this.startTime = startTime;
        this.endTime = endTime;
        this.ticketPrice = movie.getTicketPrice();
        this.totalSeats = movie.getNumberOfSeats();
        this.availableSeats = totalSeats;
        this.roomId = roomId;
    }

    // Business methods
    public boolean hasAvailableSeats() {
        return availableSeats > 0;
    }
    
    public boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }
    
    public void cancelBooking() {
        if (availableSeats < totalSeats) {
            availableSeats++;
        }
    }
    
    public String getFormattedStartTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return startTime.format(formatter);
    }
    
    public String getFormattedEndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return endTime.format(formatter);
    }
    
    public boolean isShowTimeActive() {
        LocalDateTime now = LocalDateTime.now();
        return now.isBefore(endTime);
    }
    
    public String getMovieTitle() {
        return movie != null ? movie.getTitle() : "";
    }
    
    public String getMovieGenre() {
        return movie != null ? movie.getGenre() : "";
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        if (movie != null) {
            this.ticketPrice = movie.getTicketPrice();
            this.totalSeats = movie.getNumberOfSeats();
        }
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }
    
    @Override
    public String toString() {
        return "ShowTime{" +
                "id='" + id + '\'' +
                ", movie='" + getMovieTitle() + '\'' +
                ", genre='" + getMovieGenre() + '\'' +
                ", startTime=" + getFormattedStartTime() +
                ", endTime=" + getFormattedEndTime() +
                ", ticketPrice=" + ticketPrice +
                ", availableSeats=" + availableSeats + "/" + totalSeats +
                ", roomId='" + roomId + '\'' +
                '}';
    }
}
