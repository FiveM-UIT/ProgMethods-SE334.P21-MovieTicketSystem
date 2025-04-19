package com.se356p21bt1.movie_ticket_system.repository;

import com.se356p21bt1.movie_ticket_system.model.Ticket;
import com.se356p21bt1.movie_ticket_system.model.Movie;
import com.se356p21bt1.movie_ticket_system.model.Seat;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class TicketRepository {
    private final Map<String, Ticket> tickets = new HashMap<>();
    private final Map<Integer, List<String>> seatTickets = new HashMap<>();
    private final Map<String, List<String>> movieTickets = new HashMap<>();

    public Ticket save(Ticket ticket) {
        String id = UUID.randomUUID().toString();
        tickets.put(id, ticket);
        
        // Index by seat number
        seatTickets.computeIfAbsent(ticket.getSeat().getSeatNumber(), k -> new ArrayList<>())
                   .add(id);
        
        // Index by movie
        movieTickets.computeIfAbsent(ticket.getMovie().getTitle(), k -> new ArrayList<>())
                   .add(id);
        
        return ticket;
    }

    public Optional<Ticket> findById(String id) {
        return Optional.ofNullable(tickets.get(id));
    }

    public List<Ticket> findAll() {
        return new ArrayList<>(tickets.values());
    }

    public List<Ticket> findBySeatNumber(int seatNumber) {
        return seatTickets.getOrDefault(seatNumber, new ArrayList<>()).stream()
                .map(tickets::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public List<Ticket> findByMovie(Movie movie) {
        return movieTickets.getOrDefault(movie.getTitle(), new ArrayList<>()).stream()
                .map(tickets::get)
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public boolean existsById(String id) {
        return tickets.containsKey(id);
    }

    public void deleteById(String id) {
        Ticket ticket = tickets.remove(id);
        if (ticket != null) {
            // Remove from seat index
            List<String> seatTicketList = seatTickets.get(ticket.getSeat().getSeatNumber());
            if (seatTicketList != null) {
                seatTicketList.remove(id);
            }
            
            // Remove from movie index
            List<String> movieTicketList = movieTickets.get(ticket.getMovie().getTitle());
            if (movieTicketList != null) {
                movieTicketList.remove(id);
            }
        }
    }

    public void deleteByMovie(Movie movie) {
        List<String> ticketsToRemove = new ArrayList<>(
            movieTickets.getOrDefault(movie.getTitle(), new ArrayList<>())
        );
        ticketsToRemove.forEach(this::deleteById);
    }

    public int countTicketsByMovie(Movie movie) {
        return movieTickets.getOrDefault(movie.getTitle(), new ArrayList<>()).size();
    }

    public void deleteAll() {
        tickets.clear();
        seatTickets.clear();
        movieTickets.clear();
    }
}
