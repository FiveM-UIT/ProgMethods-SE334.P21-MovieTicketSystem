package com.se356p21bt1.movie_ticket_system.model;

public class Seat {
    private int seatNumber;
    private SeatStatus status;
    
    public enum SeatStatus {
        AVAILABLE,
        SOLD,
        CANCELED
    }

    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
        this.status = SeatStatus.AVAILABLE;
    }

    public int getSeatNumber() {
        return seatNumber;
    }
    
    public SeatStatus getStatus() {
        return status;
    }
    
    public boolean isAvailable() {
        return status == SeatStatus.AVAILABLE;
    }
    
    public void reserve() {
        this.status = SeatStatus.SOLD;
    }
    
    public void cancel() {
        this.status = SeatStatus.CANCELED;
    }
    
    public void reset() {
        this.status = SeatStatus.AVAILABLE;
    }
}