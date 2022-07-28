package com.tutorials.msflight.repo;

import com.tutorials.msflight.entity.Flight;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    Optional<Flight> findFlightByFlightIdAndActiveTrue(UUID flightId);

    List<Flight> findAllByActiveTrue();
}
