package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationRepository;

import java.util.List;

@Controller
public class ReservationController {

    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository repository) {
        this.reservationRepository = repository;
    }

    @GetMapping("/reservations")
    public ResponseEntity<List<Reservation>> checkReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        return ResponseEntity.ok(reservations);
    }
}
