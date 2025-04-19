package com.se356p21bt1.movie_ticket_system.service;

import com.se356p21bt1.movie_ticket_system.model.Seat;

import java.util.concurrent.locks.ReentrantLock;

public class TicketService {
    private final ReentrantLock lock = new ReentrantLock();
    public boolean reserveSeat(Seat seat) {
        lock.lock();
        try {
            if (seat.isAvailable()) {
                seat.reserve(); // Đánh dấu ghế là đã bán
                return true;
            }
            return false;
        } finally {
            lock.unlock();
        }
    }
}
