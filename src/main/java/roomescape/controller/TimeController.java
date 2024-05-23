package roomescape.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import roomescape.domain.dto.RequestTime;
import roomescape.domain.dto.ResponseTime;
import roomescape.repository.TimeRepository;

import java.net.URI;
import java.util.List;

@RequestMapping("/times")
@Controller
public class TimeController {

    @Autowired
    private final TimeRepository timeRepository;

    @Autowired // 생성자가 하나일 때는 생략 가능
    public TimeController(TimeRepository timeRepository) {
        this.timeRepository = timeRepository;
    }

    @GetMapping
    ResponseEntity<List<ResponseTime>> checkTime() {
        return ResponseEntity.ok().body(timeRepository.findAll());
    }

    @PostMapping
    ResponseEntity<ResponseTime> saveTime(@RequestBody RequestTime requestTime) {
        ResponseTime responseTime = timeRepository.addTime(requestTime);

        URI location = URI.create("/times/" + responseTime.id());

        return ResponseEntity.created(location).body(responseTime);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> deleteTime(@PathVariable long id) {
        timeRepository.deleteReservationById(id);

        return ResponseEntity.noContent().build();
    }


}
