package roomescape.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roomescape.domain.dto.RequestReservation;
import roomescape.domain.Reservation;
import roomescape.domain.ReservationRepository;
import roomescape.domain.dto.ResponseReservation;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RequestMapping("/reservations")
@Controller
public class ReservationController {

    private final ReservationRepository reservationRepository;

    public ReservationController(ReservationRepository repository) {
        this.reservationRepository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Reservation>> checkReservations() {
        List<Reservation> reservations = reservationRepository.findAll();

        return ResponseEntity.ok(reservations);
    }

    @PostMapping
    public ResponseEntity<ResponseReservation> addReservation(@RequestBody RequestReservation request){

        Reservation newReservation = request.toReservation();
        reservationRepository.addReservation(newReservation);

        URI location = URI.create("/reservations/" + newReservation.getId());
        ResponseReservation responseReservation = ResponseReservation.from(newReservation);

        return ResponseEntity.created(location).body(responseReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        Reservation reservation = reservationRepository.findAll().stream()
                .filter(it -> Objects.equals(it.getId(), id))
                .findFirst()
                .orElseThrow(RuntimeException::new);

        reservationRepository.deleteReservation(reservation);

        return ResponseEntity.noContent().build();
    }
}
