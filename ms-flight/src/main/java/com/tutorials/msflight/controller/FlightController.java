package com.tutorials.msflight.controller;

import com.tutorials.msflight.model.CreateFlightRqModel;
import com.tutorials.msflight.model.FlightRsModel;
import com.tutorials.msflight.model.UpdateFlightRqModel;
import com.tutorials.msflight.service.FlightService;
import java.util.List;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@AllArgsConstructor
@RestController
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public ResponseEntity<FlightRsModel> createFlight(@Valid @RequestBody CreateFlightRqModel request) {
        var response = flightService.createFlight(request);
        return ResponseEntity.ok().body(response);
    }

    @PutMapping("/id")
    public ResponseEntity<FlightRsModel> updateFlight(@Valid @RequestBody UpdateFlightRqModel request) {
        var response = flightService.updateFlight(request);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping
    public ResponseEntity<List<FlightRsModel>> getFlights() {
        var response = flightService.getFlights();
        return ResponseEntity.ok().body(response);
    }

}
