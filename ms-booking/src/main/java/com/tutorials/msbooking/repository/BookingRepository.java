package com.tutorials.msbooking.repository;

import com.tutorials.msbooking.entity.Booking;
import com.tutorials.msbooking.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    Optional<Booking> findByBookingIdAndUserAndActiveTrue(UUID id, User user);

    List<Booking> findAllByUserAndActiveTrue(User user);

    default List<Booking> findAllByFlightId(UUID flightId) {
        return findAllByFlight_FlightId(flightId);
    }

    List<Booking> findAllByFlight_FlightId(UUID flightId);
}
