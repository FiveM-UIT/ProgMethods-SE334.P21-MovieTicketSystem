package com.se356p21bt1.movie_ticket_system.controller;

import com.se356p21bt1.movie_ticket_system.model.Movie;
import com.se356p21bt1.movie_ticket_system.model.Seat;
import com.se356p21bt1.movie_ticket_system.repository.SeatRepository;
import com.se356p21bt1.movie_ticket_system.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class TicketController {
    private final SeatRepository seatRepo = new SeatRepository(10); // Giả sử có 10 ghế
    private final TicketService ticketService = new TicketService();
    // Initialize Movie with required parameters
    private final Movie movie = new Movie("Sample Movie", "Action", 10.0, "19:30", 10);

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("seats", seatRepo.getSeats());
        model.addAttribute("movie", movie);
        return "index";
    }
    @PostMapping("/reserve")
    public String reserveSeat(int seatNumber, Model model) {
        Seat seat = seatRepo.getSeat(seatNumber);
        boolean success = ticketService.reserveSeat(seat);
        if (success) {
            model.addAttribute("message", "Đặt vé thành công cho ghế số " + seatNumber);
        } else {
            model.addAttribute("message", "Ghế số " + seatNumber + " đã được đặt.");
        }
        model.addAttribute("seats", seatRepo.getSeats());
        model.addAttribute("movie", movie);
        return "index";
    }
}